package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.domain.repository.LancamentoFinanceiroRepository;
import com.br.workdate.apiworkdate.rest.dto.ListLancamentoFinanceiroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoFinanceiroController {
    @Autowired
    private LancamentoFinanceiroRepository lancamentoFinanceiroRepository;
    @GetMapping
    public Page<ListLancamentoFinanceiroDTO> findAllLancamentos(Pageable pageable){
        var page = lancamentoFinanceiroRepository.findAll(pageable).map(ListLancamentoFinanceiroDTO::new);
        return page;
    }
}
