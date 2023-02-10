package com.br.workdate.apiworkdate.controller;

import com.br.workdate.apiworkdate.domain.agendamentos.Agendamento;
import com.br.workdate.apiworkdate.domain.agendamentos.AgendamentoData;
import com.br.workdate.apiworkdate.domain.agendamentos.AgendamentoRepository;
import com.br.workdate.apiworkdate.domain.agendamentos.ListAgendamentoData;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity addAgendamento(@RequestBody @Valid AgendamentoData data, UriComponentsBuilder uriBuilder){
        var agendamento = new Agendamento(data);
        repository.save(agendamento);
        var uri = uriBuilder.path("/agendamento/{id}").buildAndExpand(agendamento).toUri();
        return ResponseEntity.created(uri).body(data);
    }
    @GetMapping
    public ResponseEntity<Page<ListAgendamentoData>> listAgendamento(@PageableDefault(sort = "id") Pageable pageable){
        var page = repository.findAll(pageable).map(ListAgendamentoData::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity findAgendamento(@PathVariable Long id){
        var agendamento = repository.getReferenceById(id);
        return ResponseEntity.ok(new ListAgendamentoData(agendamento));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteAgendamento(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
