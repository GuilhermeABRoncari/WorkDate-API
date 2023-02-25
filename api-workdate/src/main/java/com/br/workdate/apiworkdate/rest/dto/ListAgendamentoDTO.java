package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.Cliente;
import com.br.workdate.apiworkdate.domain.entity.Servico;

import java.sql.Timestamp;

public record ListAgendamentoDTO(
        Long id,
        Long cliente_id,
        Long servico_id,
        Timestamp horario) {
    public ListAgendamentoDTO(Agendamento agendamento){
        this(agendamento.getId(), agendamento.getCliente().getId(), agendamento.getServico().getId(), agendamento.getHorario());
    }
}
