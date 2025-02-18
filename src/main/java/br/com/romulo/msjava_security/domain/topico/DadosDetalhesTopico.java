package br.com.romulo.msjava_security.domain.topico;

import java.util.List;

import br.com.romulo.msjava_security.domain.resposta.DadosListagemResposta;
import br.com.romulo.msjava_security.domain.resposta.Resposta;

public record DadosDetalhesTopico(DadosListagemTopico dadosListagem, List<DadosListagemResposta> respostas) {
    public DadosDetalhesTopico(Topico topico, List<Resposta> respostas) {
        this(new DadosListagemTopico(topico), respostas.stream().map(DadosListagemResposta::new).toList());
    }
}
