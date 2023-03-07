package com.br.workdate.apiworkdate.service;

import com.br.workdate.apiworkdate.domain.Situation;
import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.domain.repository.LancamentoFinanceiroRepository;
import com.br.workdate.apiworkdate.domain.repository.ServicoRepository;
import com.br.workdate.apiworkdate.infra.AgendamentoException;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoDTO;
import com.br.workdate.apiworkdate.rest.response.AgendamentoResponse;
import com.br.workdate.apiworkdate.service.validations.WorkDateValidations;
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
    @Autowired
    private WorkDateValidations workDateValidations;
    private static  final String NOT_ALOWED = "Horario escolhido já está agendado.";
    private static  final String AGENDAMENTO_NOT_FOUND = "Agendamento não encontrado.";
    private static  final String AGENDAMENTO_NOT_EXISTS = "Agendamento não existe.";

    public AgendamentoResponse save(AgendamentoDTO agendamentoDTO) {
        if (!agendamentoRepository.existsByHorario(agendamentoDTO.horario())) {

            workDateValidations.isValid(agendamentoDTO);

            var cliente = clienteRepository.getReferenceById(agendamentoDTO.cliente_id());
            var servico = servicoRepository.getReferenceById(agendamentoDTO.servico_id());
            cliente.setAgendado(true);
            servico.setAgendado(true);
            var agendamento = new Agendamento(null, agendamentoDTO.horario(), cliente, servico, agendamentoDTO.observacoes(), false, false);
            agendamentoRepository.save(agendamento);
            lancamentoFinanceiroRepository.save(new LancamentoFinanceiro(null, agendamento, Situation.OPEN));

            return new AgendamentoResponse(agendamento);
        } else throw new AgendamentoException(NOT_ALOWED);
    }

    public void delete(Long id) {
        agendamentoRepository.findById(id).map(agendamento -> {
            agendamento.getCliente().setAgendado(false);
            agendamento.getServico().setAgendado(false);

            var lancamentoFinanceiro = lancamentoFinanceiroRepository.findByAgendamentoId(id);
            lancamentoFinanceiroRepository.delete(lancamentoFinanceiro);

            agendamentoRepository.delete(agendamento);
            return agendamento;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, AGENDAMENTO_NOT_FOUND));
    }

    public void concluir(Long id) {
        Agendamento agendamento = agendamentoRepository.getReferenceById(id);
        if (!agendamento.getConcluido()) {
            agendamento.concluir();
            LancamentoFinanceiro lancamentoFinanceiro = lancamentoFinanceiroRepository.findByAgendamentoId(agendamento.getId());
            if (lancamentoFinanceiro.getSituation() == Situation.OPEN)
                lancamentoFinanceiro.situationChange(Situation.PAID);
        } else if (agendamento.getConcluido()) {
            agendamento.setConcluido(false);
        }
    }

    public void update(AgendamentoDTO agendamentoDTO) {
        if (agendamentoRepository.existsById(agendamentoDTO.id())) {

            workDateValidations.isValid(agendamentoDTO);

            var agendamento = agendamentoRepository.getReferenceById(agendamentoDTO.id());
            var cliente = clienteRepository.getReferenceById(agendamentoDTO.cliente_id());
            var servico = servicoRepository.getReferenceById(agendamentoDTO.servico_id());
            agendamento.update(cliente, servico, agendamentoDTO.horario());
        } else throw new AgendamentoException(AGENDAMENTO_NOT_EXISTS);
    }
}
