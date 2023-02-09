package com.br.workdate.apiworkdate.domain.clientes;

public record DataClienteList(Long id, String nome, String endereco, String fone) {
    public DataClienteList(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getFone());
    }
}
