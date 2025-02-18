package br.com.romulo.msjava_security.domain.curso;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.romulo.msjava_security.infra.exeption.RegraDeNegocioException;

@Service
public class CursoService {
	private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    public Curso buscarPeloId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegraDeNegocioException("Curso não encontrado!"));
    }

    public Page<DadosCurso> listar(Categoria categoria, Pageable paginacao) {
        if(categoria != null)
            return repository.findByCategoria(categoria, paginacao).map(DadosCurso::new);
        return repository.findAll(paginacao).map(DadosCurso::new);

    }
}
