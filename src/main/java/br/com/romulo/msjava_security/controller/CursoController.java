package br.com.romulo.msjava_security.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.romulo.msjava_security.domain.curso.Categoria;
import br.com.romulo.msjava_security.domain.curso.CursoService;
import br.com.romulo.msjava_security.domain.curso.DadosCurso;

@RestController
@RequestMapping("/cursos")
public class CursoController {
	private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<DadosCurso>> listar(@RequestParam(required = false) Categoria categoria,
                                                   @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        var pagina = service.listar(categoria, paginacao);
        return ResponseEntity.ok(pagina);
    }
}
