package br.com.romulo.msjava_security.domain.curso;

public record DadosCurso(Long id, String nome, Categoria categoria) {
    public DadosCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}