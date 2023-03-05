package com.br.workdate.apiworkdate.rest.response;

import com.br.workdate.apiworkdate.domain.entity.Servico;

import java.math.BigDecimal;

public record ServicoResponse(Long id, String descricao, BigDecimal valor) {
    public ServicoResponse(Servico servico){
        this(servico.getId(), servico.getDescricao(), servico.getValor());
    }
}
