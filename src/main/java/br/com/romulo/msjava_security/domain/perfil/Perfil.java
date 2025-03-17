package br.com.romulo.msjava_security.domain.perfil;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfis")
public class Perfil implements GrantedAuthority{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Enumerated(EnumType.STRING)
	private PerfilNome nome;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PerfilNome getNome() {
		return nome;
	}
	public void setNome(PerfilNome nome) {
		this.nome = nome;
	}
	
	
	@Override
	public String getAuthority() {
		
		return "ROLE_" + nome;
	}
	
	
}
