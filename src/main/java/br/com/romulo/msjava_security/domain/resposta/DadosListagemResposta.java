package br.com.romulo.msjava_security.domain.resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(
        Long id,
        String mensagem,
        String autor,
        LocalDateTime dataCriacao,
        Boolean solucao
) {
    public DadosListagemResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getAutor(), resposta.getDataCriacao(), resposta.ehSolucao());
    }
}
