package com.br.workdate.apiworkdate.rest.dto;


import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record ListLancamentoFinanceiroDTO(
        String clientName,
        String serviceDescription,
        BigDecimal value,
        Timestamp date) {

    public ListLancamentoFinanceiroDTO(LancamentoFinanceiro lancamentoFinanceiro) {
        this(
                lancamentoFinanceiro.getAgendamento().getCliente().getNome(),
                lancamentoFinanceiro.getAgendamento().getServico().getDescricao(),
                lancamentoFinanceiro.getAgendamento().getServico().getValor(),
                lancamentoFinanceiro.getAgendamento().getHorario()
                );
    }
}
