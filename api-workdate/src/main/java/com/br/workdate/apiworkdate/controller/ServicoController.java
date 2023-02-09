package com.br.workdate.apiworkdate.controller;

import com.br.workdate.apiworkdate.servicos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoRepository repository;

    @PostMapping
    @Transactional
    public void addServico(@RequestBody @Valid ServicoData data) {
        repository.save(new Servicos(data));
    }

    @GetMapping
    public Page<DataServicoList> listServicos(@PageableDefault(sort = "descricao") Pageable pageable) {
        return repository.findAll(pageable).map(DataServicoList::new);
    }
    @PutMapping
    @Transactional
    public void attServico(@RequestBody @Valid UpdateServico updateServico){
        var servico =repository.getReferenceById(updateServico.id());
        servico.att(updateServico);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteServico(@PathVariable Long id){
        repository.deleteById(id);
    }
}
