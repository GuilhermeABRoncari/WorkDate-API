package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Cliente;
import jakarta.validation.constraints.NotBlank;

public record UpdateClientesDTO(
        String nome,
        String endereco,
        String fone) {
    public UpdateClientesDTO(Cliente cliente) {
        this(cliente.getNome(), cliente.getEndereco(), cliente.getFone());
    }
}
