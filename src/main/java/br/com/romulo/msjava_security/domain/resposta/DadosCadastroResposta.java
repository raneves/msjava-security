package br.com.romulo.msjava_security.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResposta(
        @NotBlank String mensagem,
        @NotBlank String autor) {
}
