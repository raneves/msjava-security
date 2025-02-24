package br.com.romulo.msjava_security.domain.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(@NotBlank String email,
        @NotBlank String senha) {
}
