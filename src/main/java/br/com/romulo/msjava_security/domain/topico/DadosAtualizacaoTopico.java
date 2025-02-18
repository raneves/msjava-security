package br.com.romulo.msjava_security.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
	 @NotNull Long id,
     String titulo,
     String mensagem,
     Long cursoId
		) {
}
