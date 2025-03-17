package br.com.romulo.msjava_security.infra.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;


@Configuration
@EnableWebSecurity
public class ConfiguracoesSeguranca {
	private final FiltroTokenAcesso filtroTokenAcesso;

	public ConfiguracoesSeguranca(FiltroTokenAcesso filtroTokenAcesso) {
		this.filtroTokenAcesso = filtroTokenAcesso;
	}
	
	
	@Bean
    public SecurityFilterChain filtrosSeguranca(HttpSecurity http) throws Exception {
        return http
        		.authorizeHttpRequests(
        				req -> {
        					req.requestMatchers("/login", "/atualizar-token", "/registrar", "verificar-conta").permitAll();

        		            req.requestMatchers(HttpMethod.GET, "/cursos").permitAll();
        		            req.requestMatchers(HttpMethod.GET, "/topicos/**").permitAll();

        		            req.requestMatchers(HttpMethod.POST, "/topicos").hasRole("ESTUDANTE");
        		            req.requestMatchers(HttpMethod.PUT, "/topicos").hasRole("ESTUDANTE");
        		            req.requestMatchers(HttpMethod.DELETE, "/topicos/**").hasRole("ESTUDANTE");

        		            req.requestMatchers(HttpMethod.PATCH, "/topicos/{idTopico}/respostas/**").hasAnyRole("INSTRUTOR", "ESTUDANTE");
        		            
        		            req.requestMatchers(HttpMethod.PATCH, "/topicos/**").hasRole("MODERADOR");

        		            req.requestMatchers(HttpMethod.PATCH, "/adicionar-perfil/**").hasRole("ADMIN");

        		            req.anyRequest().authenticated();
        		        }
                    )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(filtroTokenAcesso, UsernamePasswordAuthenticationFilter.class) // este filtro tem que executar antes do filtro de autenticacao do spring
                .build();
    }

    @Bean
    public PasswordEncoder encriptador(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public RoleHierarchy hierarquiaPerfis(){
    	String hierarquia = "ROLE_ADMIN > ROLE_MODERADOR\n"+
                "ROLE_MODERADOR > ROLE_INSTRUTOR\n"+
                "ROLE_MODERADOR > ROLE_ESTUDANTE";
    	return RoleHierarchyImpl.fromHierarchy(hierarquia);
    }
    /**
     *  outro exemplo de implementacao de hierarquia
     * @Bean
    public RoleHierarchy hierarquiaPerfis(){
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMIN").implies("MODERADOR")
                .role("MODERADOR").implies("ESTUDANTE", "INSTRUTOR")
                .build();
    }
     * 
     */
}