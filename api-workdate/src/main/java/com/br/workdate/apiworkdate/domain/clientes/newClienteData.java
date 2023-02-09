package com.br.workdate.apiworkdate.domain.clientes;

public record newClienteData(Long id, String nome, String endereco, String fone) {
    public newClienteData(Cliente cliente){
        this(cliente.getId(), cliente.getNome(), cliente.getEndereco(), cliente.getFone());
    }
}
