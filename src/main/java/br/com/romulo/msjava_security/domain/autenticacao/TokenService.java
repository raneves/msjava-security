package br.com.romulo.msjava_security.domain.autenticacao;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import br.com.romulo.msjava_security.domain.usuario.Usuario;
import br.com.romulo.msjava_security.infra.exeption.RegraDeNegocioException;

@Service
public class TokenService {
	public String gerarToken(Usuario usuario) {
		String token = null;
        try {
                Algorithm algorithm = Algorithm.HMAC256("12345678");//senha
                token = JWT.create()
                        .withIssuer("Forum Hub")//dono da apliccao o issue
                        .withSubject(usuario.getUsername()) //devolçver o email do usuario
                        .withExpiresAt(expiracao(30))    //prazo de validade do token, no caso 30 minutos
                        .sign(algorithm);
        } catch (JWTCreationException exception){
           throw new RegraDeNegocioException("Erro ao gerar token JWT de acesso");
        }
        return token;
	}
	
	
	 public String gerarRefreshToken(Usuario usuario) {
		 //bem semelhante ao gerarToken, o que muda eh ao inves de retornar o nome do usuarioo retornarmos o id
    	try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            return JWT.create()
                .withIssuer("Forum Hub")
                .withSubject(usuario.getId().toString())//aqui retornamaos o id
                .withExpiresAt(expiracao(120))//2 horas, ou seja 120 minutos
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RegraDeNegocioException("Erro ao gerar token JWT de acesso!");
        }
    }
	
	private Instant expiracao(Integer minutos) {
        return LocalDateTime.now().plusMinutes(minutos).toInstant(ZoneOffset.of("-03:00"));
    }
	
	
	public String verificarToken(String token){
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256("12345678");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Forum Hub")
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            throw new RegraDeNegocioException("Erro ao verificar token JWT de acesso!");
        }
    }
	
	
}
