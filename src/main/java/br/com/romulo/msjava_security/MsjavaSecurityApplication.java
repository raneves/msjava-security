package br.com.romulo.msjava_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsjavaSecurityApplication {

	public static void main(String[] args) {
		
		//utilizar o site https://jwt.io/ para verificar o token 
		
	    //senha cadastrada no banco eh: maria123, utilizamos o site https://bcrypt.online/ para criptografar
	    //o login eh maria@email.com
		
		//usuario admin, joao senha joao123: $2y$10$6SJzkS0GDFMqcXSWmPc5LuDeXjIXB.gbbD0HGlVVh.Og1MH8HXxS2
		
		SpringApplication.run(MsjavaSecurityApplication.class, args);
	}

}
