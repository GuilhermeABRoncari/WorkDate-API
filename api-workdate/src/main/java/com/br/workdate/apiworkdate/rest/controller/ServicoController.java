package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.rest.dto.ListServicoDTO;
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
    public Page<ListServicoDTO> listServicos(@PageableDefault(sort = "descricao") Pageable pageable) {
        var page = servicoRepository.findAll(pageable).map(ListServicoDTO::new);
        return page;
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void attServico(@PathVariable Long id, @RequestBody @Valid UpdateServicoDTO updateServicoDTO) {
        servicoRepository.findById(id).map(servicoAtual -> {
            var servico = servicoRepository.getReferenceById(id);
            servico.att(updateServicoDTO);
            servicoRepository.save(servico);
            return servico;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado"));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServico(@PathVariable Long id) {
        servicoRepository.findById(id).map(servico -> {
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
