package com.br.workdate.apiworkdate.domain.agendamentos;

import com.br.workdate.apiworkdate.domain.clientes.Cliente;
import com.br.workdate.apiworkdate.domain.servicos.Servicos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record AgendamentoData(
        Cliente cliente,
        Servicos servico,
        @NotNull
        Timestamp horario) {
    public AgendamentoData(Agendamento agendamento){
        this(agendamento.getCliente(), agendamento.getServico(), agendamento.getHorario());
    }
}
