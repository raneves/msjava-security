package br.com.romulo.msjava_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsjavaSecurityApplication {

	public static void main(String[] args) {
		
	    //senha cadastrada no banco eh: maria123, utilizamos o site https://bcrypt.online/ para criptografar
	    //o login eh maria@email.com
		
		SpringApplication.run(MsjavaSecurityApplication.class, args);
	}

}
