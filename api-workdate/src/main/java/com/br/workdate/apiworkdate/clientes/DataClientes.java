package com.br.workdate.apiworkdate.clientes;

import jakarta.validation.constraints.NotBlank;

public record DataClientes(
        @NotBlank
        String nome,
        String endereco,
        @NotBlank
        String fone) {
        public DataClientes(Cliente cliente){
                this(cliente.getNome(), cliente.getEndereco(), cliente.getFone());
        }
}
