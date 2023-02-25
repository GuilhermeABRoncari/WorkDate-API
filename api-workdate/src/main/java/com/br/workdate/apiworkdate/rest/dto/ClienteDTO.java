package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Cliente;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        @NotBlank
        String nome,
        String endereco,
        @NotBlank
        String fone) {
        public ClienteDTO(Cliente cliente){
                this(cliente.getNome(), cliente.getEndereco(), cliente.getFone());
        }
}
