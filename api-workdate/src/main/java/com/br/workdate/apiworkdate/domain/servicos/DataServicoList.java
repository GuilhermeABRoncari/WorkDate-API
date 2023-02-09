package com.br.workdate.apiworkdate.domain.servicos;

import java.math.BigDecimal;

public record DataServicoList(Long id, String descricao, BigDecimal valor) {
    public DataServicoList(Servicos servicos){
        this(servicos.getId(), servicos.getDescricao(), servicos.getValor());
    }
}
