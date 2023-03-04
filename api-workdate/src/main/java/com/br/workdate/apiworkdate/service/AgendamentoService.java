package com.br.workdate.apiworkdate.service;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.domain.repository.LancamentoFinanceiroRepository;
import com.br.workdate.apiworkdate.domain.repository.ServicoRepository;
import com.br.workdate.apiworkdate.domain.Situation;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoDTO;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        var agendamento = new Agendamento(null, agendamentoDTO.horario(), cliente, servico, agendamentoDTO.observacoes(), false, false);
        agendamentoRepository.save(agendamento);
        lancamentoFinanceiroRepository.save(new LancamentoFinanceiro(null, agendamento, Situation.OPEN));
        return agendamento;
    }

    public void delete(Long id) {
        agendamentoRepository.findById(id).map(agendamento -> {
            agendamento.getCliente().setAgendado(false);
            agendamento.getServico().setAgendado(false);

            var lancamentoFinanceiro = lancamentoFinanceiroRepository.findByAgendamentoId(id);
            lancamentoFinanceiroRepository.delete(lancamentoFinanceiro);

            agendamentoRepository.delete(agendamento);
            return agendamento;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado."));
    }

    public void concluir(Long id) {
        Agendamento agendamento = agendamentoRepository.getReferenceById(id);
        if (!agendamento.getConcluido()) {
            agendamento.concluir();
            LancamentoFinanceiro lancamentoFinanceiro = lancamentoFinanceiroRepository.findByAgendamentoId(agendamento.getId());
            if (lancamentoFinanceiro.getSituation() == Situation.OPEN) lancamentoFinanceiro.situationChange(Situation.PAID);
        } else if (agendamento.getConcluido()) {
            agendamento.setConcluido(false);
        }
    }

    public void update(AgendamentoResponse agendamentoResponse) {
        var agendamento = agendamentoRepository.getReferenceById(agendamentoResponse.id());
        agendamentoRepository.save(agendamento);
    }
}
