package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.infra.AgendamentoException;
import com.br.workdate.apiworkdate.rest.dto.ServicoResponse;
import com.br.workdate.apiworkdate.rest.dto.ServicoDTO;
import com.br.workdate.apiworkdate.rest.dto.UpdateServicoDTO;
import com.br.workdate.apiworkdate.domain.entity.Servico;
import com.br.workdate.apiworkdate.domain.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
    @Autowired
    private ServicoRepository servicoRepository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Servico saveServico(@RequestBody @Valid ServicoDTO data) {
        var servico = new Servico(data);
        servicoRepository.save(servico);
        return servico;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ServicoResponse> listServicos(@PageableDefault(sort = "descricao") Pageable pageable) {
        return servicoRepository.findAll(pageable).map(ServicoResponse::new);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void updateServico(@PathVariable Long id, @RequestBody @Valid UpdateServicoDTO updateServicoDTO) {
        servicoRepository.findById(id).map(servicoAtual -> {
            var servico = servicoRepository.getReferenceById(id);
            servico.update(updateServicoDTO);
            servicoRepository.save(servico);
            return servico;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado"));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServico(@PathVariable Long id) {
        servicoRepository.findById(id).map(servico -> {
            if(servico.isAgendado()){
                throw new AgendamentoException("Serviço não pode ser excluído pois está em uso em um agendamento.");
            }
            servicoRepository.delete(servico);
            return servico;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado."));
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Servico findServico(@PathVariable Long id){
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado."));
    }
}
