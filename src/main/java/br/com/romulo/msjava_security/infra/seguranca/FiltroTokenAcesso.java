package br.com.romulo.msjava_security.infra.seguranca;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.romulo.msjava_security.domain.autenticacao.TokenService;
import br.com.romulo.msjava_security.domain.usuario.Usuario;
import br.com.romulo.msjava_security.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * classe que será o filtro do Spring Security. Chamaremos essa classe de "FiltroTokenAcesso", pois ela filtrará o token de acesso do cliente.
 */
@Component //o filtro sera gerenciado pelo spring
public class FiltroTokenAcesso extends OncePerRequestFilter{
	private final TokenService tokenService;
	private final UsuarioRepository usuarioRepository;
	
	public FiltroTokenAcesso(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarTokenRequisicao(request);
		//recuperar o token da requisicao

        if(token != null){
            //validacao do token
        	String email = tokenService.verificarToken(token);
        	//com o email validado pelo token, buscar o usuario no banco atraves do email, se nao encontrar lancar uma exception
        	 Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email).orElseThrow();
        	 
        	 Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        	 SecurityContextHolder.getContext().setAuthentication(authentication);///setar a autenticacao no contexto
        	 
        }
            
        filterChain.doFilter(request, response);
		
	}
	
	private String recuperarTokenRequisicao(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
	}

}
