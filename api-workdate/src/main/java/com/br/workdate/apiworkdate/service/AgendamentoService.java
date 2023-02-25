package com.br.workdate.apiworkdate.service;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.domain.repository.ServicoRepository;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    public Agendamento save(AgendamentoDTO agendamentoDTO) {
        var cliente = clienteRepository.getReferenceById(agendamentoDTO.cliente_id());
        var servico = servicoRepository.getReferenceById(agendamentoDTO.servico_id());
        Agendamento agendamento = new Agendamento(null, agendamentoDTO.horario(), cliente, servico);
        agendamentoRepository.save(agendamento);
        return agendamento;
    }
}
