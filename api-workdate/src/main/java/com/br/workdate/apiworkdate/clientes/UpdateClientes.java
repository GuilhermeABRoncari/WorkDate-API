package com.br.workdate.apiworkdate.clientes;

import jakarta.validation.constraints.NotNull;

public record UpdateClientes(
        @NotNull
        Long id,
        String nome,
        String endereco,
        String fone) {
}
