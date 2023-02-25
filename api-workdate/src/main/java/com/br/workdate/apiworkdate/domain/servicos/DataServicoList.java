package com.br.workdate.apiworkdate.domain.servicos;

import com.br.workdate.apiworkdate.domain.entity.Servico;

import java.math.BigDecimal;

public record DataServicoList(Long id, String descricao, BigDecimal valor) {
    public DataServicoList(Servico servico){
        this(servico.getId(), servico.getDescricao(), servico.getValor());
    }
}
