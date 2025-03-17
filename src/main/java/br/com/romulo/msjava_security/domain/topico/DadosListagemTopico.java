package br.com.romulo.msjava_security.domain.topico;


import java.time.LocalDateTime;
import java.util.List;

import br.com.romulo.msjava_security.domain.usuario.Usuario;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        String autor,
        Status status,
        LocalDateTime dataCriacao,
        Integer quantidadeRespostas,
        String curso
) {
    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor().getNomeUsuario(), topico.getStatus(), topico.getDataCriacao(), topico.getQuantidadeRespostas(), topico.getCurso().getNome());
    }
}
