package com.br.workdate.apiworkdate.service;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.domain.repository.LancamentoFinanceiroRepository;
import com.br.workdate.apiworkdate.domain.repository.ServicoRepository;
import com.br.workdate.apiworkdate.infra.Situation;
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
    @Autowired
    private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;

    public Agendamento save(AgendamentoDTO agendamentoDTO) {
        var cliente = clienteRepository.getReferenceById(agendamentoDTO.cliente_id());
        var servico = servicoRepository.getReferenceById(agendamentoDTO.servico_id());
        cliente.setAgendado(true);
        servico.setAgendado(true);
        Agendamento agendamento = new Agendamento(null, agendamentoDTO.horario(), cliente, servico, agendamentoDTO.observacoes(), false, false);
        agendamentoRepository.save(agendamento);
        lancamentoFinanceiroRepository.save(new LancamentoFinanceiro(null, agendamento, Situation.OPEN));
        return agendamento;
    }
}
