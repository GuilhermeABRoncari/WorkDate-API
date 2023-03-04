package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;
import com.br.workdate.apiworkdate.domain.repository.LancamentoFinanceiroRepository;
import com.br.workdate.apiworkdate.domain.Situation;
import com.br.workdate.apiworkdate.rest.dto.LancamentoFinanceiroResponse;
import com.br.workdate.apiworkdate.rest.dto.ResumoDTO;
import com.br.workdate.apiworkdate.service.LancamentoFinanceiroService;
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
    @Autowired
    private LancamentoFinanceiroService lancamentoFinanceiroService;

    @GetMapping
    public Page<LancamentoFinanceiroResponse> findAllLancamentos(Pageable pageable) {
        return lancamentoFinanceiroRepository.findAll(pageable).map(LancamentoFinanceiroResponse::new);
    }

    @GetMapping("/{situation}")
    public List<LancamentoFinanceiroResponse> findBySituation(@PathVariable Situation situation) {
        List<LancamentoFinanceiro> lista = lancamentoFinanceiroRepository.findAllBySituation(situation);
        return lista.stream().map(LancamentoFinanceiroResponse::new).collect(Collectors.toList());
    }
    @GetMapping("/resumo")
    public ResumoDTO resumo(){
        return lancamentoFinanceiroService.resumir();
    }
}
