package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import com.br.workdate.apiworkdate.domain.repository.AgendamentoRepository;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoDTO;
import com.br.workdate.apiworkdate.rest.response.AgendamentoResponse;
import com.br.workdate.apiworkdate.service.AgendamentoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    private static final String AGENDAMENTO_ERROR = "Agendamento não encontrado.";

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoResponse addAgendamento(@RequestBody @Valid AgendamentoDTO agendamentoDTO) {
        return agendamentoService.save(agendamentoDTO);
    }

    @GetMapping
    public Page<AgendamentoResponse> listarAgendamento(@PageableDefault(sort = "horario") Pageable pageable) {
        return agendamentoRepository.findAll(pageable).map(AgendamentoResponse::new);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Agendamento findAgendamento(@PathVariable Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, AGENDAMENTO_ERROR));
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
    public AgendamentoResponse updateAgendamento(@RequestBody @Valid AgendamentoDTO AgendamentoDTO) {
        return agendamentoService.update(AgendamentoDTO);
    }

    @GetMapping("/concluido")
    public List<Agendamento> concluidos() {
        return agendamentoRepository.findAllByConcluidoTrue();
    }

    @GetMapping("/cancelado")
    public List<Agendamento> cancelados() {
        return agendamentoRepository.findAllByCanceladoTrue();
    }
}



