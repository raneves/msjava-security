package br.com.romulo.msjava_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.romulo.msjava_security.domain.perfil.DadosPerfil;
import br.com.romulo.msjava_security.domain.usuario.DadosCadastroUsuario;
import br.com.romulo.msjava_security.domain.usuario.DadosListagemUsuario;
import br.com.romulo.msjava_security.domain.usuario.UsuarioService;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {
	private final UsuarioService usuarioService;
	
	 public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
	
	@PostMapping("/registrar")
	public ResponseEntity<DadosListagemUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
	        var usuario = usuarioService.cadastrar(dados);
	        var uri = uriBuilder.path("/{nomeUsuario}").buildAndExpand(usuario.getNomeUsuario()).toUri();
	        return ResponseEntity.created(uri).body(new DadosListagemUsuario(usuario));
	        
	}
	
    @GetMapping("/verificar-conta")
    public ResponseEntity<String> verificarEmail(@RequestParam String codigo){
        usuarioService.verificarEmail(codigo);
        return ResponseEntity.ok("Conta verificada com sucesso!");
    }
    
    @PatchMapping("adicionar-perfil/{id}")
    public ResponseEntity<DadosListagemUsuario> adicionarPerfil(@PathVariable Long id, @RequestBody @Valid DadosPerfil dados){
        var usuario = usuarioService.adicionarPerfil(id, dados);
        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }
}
