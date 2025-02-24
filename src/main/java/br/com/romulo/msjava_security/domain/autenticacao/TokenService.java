package br.com.romulo.msjava_security.domain.autenticacao;


import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import br.com.romulo.msjava_security.domain.usuario.Usuario;
import br.com.romulo.msjava_security.infra.exeption.RegraDeNegocioException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
	public String gerarToken(Usuario usuario) {
		String token = null;
        try {
                Algorithm algorithm = Algorithm.HMAC256("12345678");//senha
                token = JWT.create()
                        .withIssuer("Forn Hub")//dono da apliccao o issue
                        .withSubject(usuario.getUsername()) //devolçver o email do usuario
                        .withExpiresAt(expiracao(30))    //prazo de validade do token, no caso 30 minutos
                        .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RegraDeNegocioException("Erro ao gerar token JWT de acesso");
        }
        return token;
	}
	
	private Instant expiracao(Integer minutos) {
        return LocalDateTime.now().plusMinutes(minutos).toInstant(ZoneOffset.of("-03:00"));
}
}
