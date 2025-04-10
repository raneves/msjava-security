package br.com.romulo.msjava_security.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.romulo.msjava_security.domain.autenticacao.DadosLogin;
import br.com.romulo.msjava_security.domain.autenticacao.DadosRefreshToken;
import br.com.romulo.msjava_security.domain.autenticacao.DadosToken;
import br.com.romulo.msjava_security.domain.autenticacao.TokenService;
import br.com.romulo.msjava_security.domain.usuario.Usuario;
import br.com.romulo.msjava_security.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;


@RestController
public class AutenticacaoController {	
	
	private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService, 
    		UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<DadosToken> efetuarLogin(@Valid @RequestBody DadosLogin dados){
        var autenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = authenticationManager.authenticate(autenticationToken);

        String tokenAcesso = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        
        //Quando o servidor detecta que o token de acesso está expirado, ele verifica se o refresh token foi 
        //devolvido anteriormente e guardado pelo cliente. Em vez de redirecionar a pessoa usuária para uma página de login, 
        //o cliente envia o refresh token e recebe um novo token de acesso junto a um refresh token atualizado. Isso elimina a necessidade
        //da pessoa usuária fazer login repetidamente, melhorando a experiência
        String refreshToken = tokenService.gerarRefreshToken((Usuario) authentication.getPrincipal());
        
        
        ////utilizar o site https://jwt.io/ para verificar o token no caso tokenAcesso

        return ResponseEntity.ok(new DadosToken(tokenAcesso, refreshToken));
    }	
    
    @PostMapping("/requisitar-token")
    public ResponseEntity<DadosToken> atualizarToken(@Valid @RequestBody DadosRefreshToken dados){
    	var refreshToken = dados.refreshToken();
        Long idUsuario = Long.valueOf(tokenService.verificarToken(refreshToken)); //obter o id usuario no subject, pq para o refresh token tem o id do usuario
        var usuario = usuarioRepository.findById(idUsuario).orElseThrow();
        
        //apos recuperar o token, gerar um novo token
        String tokenAcesso = tokenService.gerarToken(usuario);
        String tokenAtualizacao = tokenService.gerarRefreshToken(usuario);
        
        return ResponseEntity.ok(new DadosToken(tokenAcesso, tokenAtualizacao));
    }
}