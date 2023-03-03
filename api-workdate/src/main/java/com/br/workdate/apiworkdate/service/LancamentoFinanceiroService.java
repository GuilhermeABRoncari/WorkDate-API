package com.br.workdate.apiworkdate.service;

import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.domain.repository.LancamentoFinanceiroRepository;
import com.br.workdate.apiworkdate.domain.repository.ServicoRepository;
import com.br.workdate.apiworkdate.infra.Situation;
import com.br.workdate.apiworkdate.rest.dto.ResumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    private static void accept(LancamentoFinanceiro lancamentoFinanceiro) {
        new BigDecimal(String.valueOf(lancamentoFinanceiro.getAgendamento().getServico().getValor()));
    }

    public ResumoDTO resumir() {
        List<LancamentoFinanceiro> lfOpen = lancamentoFinanceiroRepository.findAllBySituation(Situation.OPEN);
        List<LancamentoFinanceiro> lfPaid = lancamentoFinanceiroRepository.findAllBySituation(Situation.PAID);

        var somaOpen = lfOpen.stream().map(lancamentoFinanceiro -> lancamentoFinanceiro.getAgendamento().getServico().getValor()).collect(Collectors.toList());
        final BigDecimal[] open = new BigDecimal[1];
        open[0] = new BigDecimal(0);
        somaOpen.forEach(bigDecimal -> open[0] = bigDecimal.add(open[0]));

        var somaPaid = lfPaid.stream().map(lancamentoFinanceiro -> lancamentoFinanceiro.getAgendamento().getServico().getValor()).collect(Collectors.toList());
        final BigDecimal[] paid = new BigDecimal[1];
        paid[0] = new BigDecimal(0);
        somaPaid.forEach(bigDecimal -> paid[0] = bigDecimal.add(paid[0]));

        BigDecimal totalOpen = open[0];
        BigDecimal totalPaid = paid[0];
        BigDecimal total = totalOpen.add(totalPaid);

        return new ResumoDTO(totalOpen, totalPaid, total);
    }
}
