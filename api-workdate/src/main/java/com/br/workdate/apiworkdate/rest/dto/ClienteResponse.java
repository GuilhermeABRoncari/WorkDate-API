package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Cliente;

public record ClienteResponse(Long id, String nome, String endereco, String fone) {
    public ClienteResponse(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getFone());
    }
}
