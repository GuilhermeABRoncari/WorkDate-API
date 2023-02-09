package com.br.workdate.apiworkdate.domain.clientes;

import jakarta.validation.constraints.NotNull;

public record UpdateClientes(
        @NotNull
        Long id,
        String nome,
        String endereco,
        String fone) {
}
