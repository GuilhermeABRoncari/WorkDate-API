package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.rest.dto.ListAgendamentoDTO;
import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoDTO;
import com.br.workdate.apiworkdate.service.AgendamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private AgendamentoService agendamentoService;
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Long addAgendamento(@RequestBody @Valid AgendamentoDTO agendamentoDTO){
        Agendamento agendamento = agendamentoService.save(agendamentoDTO);
        return agendamento.getId();
    }
//    @GetMapping
//    public List<Agendamento> listarAgendamento(){
//        var lista = agendamentoRepository.findAllAgendamentos();
//        return lista;
//    }
//    @GetMapping("/{id}")
//    public Agendamento findAgendamento(@PathVariable Long id){
//        return agendamentoRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
//    }
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity deleteAgendamento(@PathVariable Long id){
//        agendamentoRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}
