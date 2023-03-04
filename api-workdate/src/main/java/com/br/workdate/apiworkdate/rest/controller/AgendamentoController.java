package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoDTO;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoResponse;
import com.br.workdate.apiworkdate.service.AgendamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public Long addAgendamento(@RequestBody @Valid AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = agendamentoService.save(agendamentoDTO);
        return agendamento.getId();
    }

    @GetMapping
    public Page<AgendamentoResponse> listarAgendamento(Pageable pageable) {
        return agendamentoRepository.findAll(pageable).map(AgendamentoResponse::new);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agendamento findAgendamento(@PathVariable Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado."));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAgendamento(@PathVariable Long id) {
        agendamentoService.delete(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public void agendamentoConcluido(@PathVariable Long id) {
        agendamentoService.concluir(id);
    }

    @PatchMapping
    @Transactional
    public void updateAgendamento(@RequestBody @Valid AgendamentoResponse agendamentoResponse) {
        agendamentoService.update(agendamentoResponse);
    }
    @GetMapping("/concluido")
    public List<Agendamento> concluidos(){
        return agendamentoRepository.findAllByConcluidoTrue();
    }
    @GetMapping("/cancelado")
    public List<Agendamento> cancelados(){
        return agendamentoRepository.findAllByCanceladoTrue();
    }
}
//Fazer um end point que retorna uma lista de agendamentos dentre o primeiro e ultimo dia,
// não retorne nada que esteja cancelado ou concluido, apenas em ABERTO(OPEN)
// eles devem estar ordenados por data e hora.

//Criar o UpdateAgendamentoDTO.
