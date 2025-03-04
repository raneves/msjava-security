package br.com.romulo.msjava_security.domain.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="usuarios")
public class Usuario implements UserDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String nomeUsuario;
    private String biografia;
    private String miniBiografia;
    
    //senha cadastrada no banco eh: maria123, utilizamos o site https://bcrypt.online/ para criptografar
    //o login eh maria@email.com
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

	public String getSenha() {
		return senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getBiografia() {
		return biografia;
	}

	public String getMiniBiografia() {
		return miniBiografia;
	} 
	public Long getId() {
        return id;
    }
	 
}
