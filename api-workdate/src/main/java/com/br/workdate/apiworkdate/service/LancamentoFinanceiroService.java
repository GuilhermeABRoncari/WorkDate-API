package com.br.workdate.apiworkdate.service;

import com.br.workdate.apiworkdate.domain.Situation;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.domain.repository.LancamentoFinanceiroRepository;
import com.br.workdate.apiworkdate.domain.repository.ServicoRepository;
import com.br.workdate.apiworkdate.rest.dto.ResumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LancamentoFinanceiroService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;

    public ResumoDTO resumir() {
        BigDecimal totalOpen = lancamentoFinanceiroRepository.findAllBySituation(Situation.OPEN)
                .stream().map(lancamentoFinanceiro -> lancamentoFinanceiro.getAgendamento().getServico().getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPaid = lancamentoFinanceiroRepository.findAllBySituation(Situation.PAID)
                .stream().map(lancamentoFinanceiro -> lancamentoFinanceiro.getAgendamento().getServico().getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new ResumoDTO(totalOpen, totalPaid, totalOpen.add(totalPaid));
    }
}
