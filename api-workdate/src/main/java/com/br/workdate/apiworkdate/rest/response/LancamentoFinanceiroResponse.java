package com.br.workdate.apiworkdate.rest.response;


import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record LancamentoFinanceiroResponse(
        String clientName,
        String serviceDescription,
        BigDecimal value,
        Timestamp date) {

    public LancamentoFinanceiroResponse(LancamentoFinanceiro lancamentoFinanceiro) {
        this(
                lancamentoFinanceiro.getAgendamento().getCliente().getNome(),
                lancamentoFinanceiro.getAgendamento().getServico().getDescricao(),
                lancamentoFinanceiro.getAgendamento().getServico().getValor(),
                lancamentoFinanceiro.getAgendamento().getHorario()
        );
    }
}
