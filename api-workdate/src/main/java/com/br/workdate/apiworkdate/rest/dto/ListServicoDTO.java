package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Servico;

import java.math.BigDecimal;

public record ListServicoDTO(Long id, String descricao, BigDecimal valor) {
    public ListServicoDTO(Servico servico){
        this(servico.getId(), servico.getDescricao(), servico.getValor());
    }
}
