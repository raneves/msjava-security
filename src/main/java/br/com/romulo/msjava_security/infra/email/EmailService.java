package br.com.romulo.msjava_security.infra.email;

import java.io.UnsupportedEncodingException;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.romulo.msjava_security.domain.usuario.Usuario;
import br.com.romulo.msjava_security.infra.exeption.RegraDeNegocioException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service

public class EmailService {
	 private final JavaMailSender enviadorEmail;
	    private static final String EMAIL_ORIGEM = "romulo.neves@gmail.com";
	    private static final String NOME_ENVIADOR = "Romulo";

	    public static final String URL_SITE = "http://localhost:8080"; //"forumhub.com.br"

	    public EmailService(JavaMailSender enviadorEmail) {
	        this.enviadorEmail = enviadorEmail;
	    }
	    @Async
	    private void enviarEmail(String emailUsuario, String assunto, String conteudo) {
	        MimeMessage message = enviadorEmail.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);

	        try {
	            helper.setFrom(EMAIL_ORIGEM, NOME_ENVIADOR);
	            helper.setTo(emailUsuario);
	            helper.setSubject(assunto);
	            helper.setText(conteudo, true);
	        } catch(MessagingException | UnsupportedEncodingException e){
	            throw new RegraDeNegocioException("Erro ao enviar email");
	        }

	        try {
	        	enviadorEmail.send(message);
	        } catch (Exception e) {
				System.out.println("não foi possivel enviar o email...."+conteudo);
			}
	        
	    }

	    public void enviarEmailVerificacao(Usuario usuario) {
	        String assunto = "Aqui está seu link para verificar o email";
	        String conteudo = gerarConteudoEmail("Olá [[name]],<br>"
	                + "Por favor clique no link abaixo para verificar sua conta:<br>"
	                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFICAR</a></h3>"
	                + "Obrigado,<br>"
	                + "Fórum Hub :).", usuario.getNomeCompleto(), URL_SITE + "/verificar-conta?codigo=" + usuario.getToken());

	        enviarEmail(usuario.getUsername(), assunto, conteudo);
	    }

	    private String gerarConteudoEmail(String template, String nome, String url) {
	    	 return template.replace("[[name]]", nome).replace("[[URL]]", url);	    
	    }
	        
}
