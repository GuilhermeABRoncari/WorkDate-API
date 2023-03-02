package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;
import com.br.workdate.apiworkdate.domain.repository.LancamentoFinanceiroRepository;
import com.br.workdate.apiworkdate.infra.Situation;
import com.br.workdate.apiworkdate.rest.dto.ListLancamentoFinanceiroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoFinanceiroController {
    @Autowired
    private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;

    @GetMapping
    public Page<ListLancamentoFinanceiroDTO> findAllLancamentos(Pageable pageable) {
        var page = lancamentoFinanceiroRepository.findAll(pageable).map(ListLancamentoFinanceiroDTO::new);
        return page;
    }

    @GetMapping("/{situation}")
    public List<ListLancamentoFinanceiroDTO> findBySituation(@PathVariable Situation situation) {
        List<LancamentoFinanceiro> lista = lancamentoFinanceiroRepository.findAllBySituation(situation);
        return lista.stream().map(ListLancamentoFinanceiroDTO::new).collect(Collectors.toList());
    }
}
