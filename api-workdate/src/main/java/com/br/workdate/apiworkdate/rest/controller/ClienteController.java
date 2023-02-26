package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.domain.entity.Cliente;
import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.infra.AgendamentoException;
import com.br.workdate.apiworkdate.rest.dto.ClienteDTO;
import com.br.workdate.apiworkdate.rest.dto.ListClienteDTO;
import com.br.workdate.apiworkdate.rest.dto.UpdateClientesDTO;
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
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        var cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ListClienteDTO> listCliente(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        var page = clienteRepository.findAll(pageable).map(ListClienteDTO::new);
        return page;
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public void attCliente(@PathVariable Long id, @RequestBody @Valid UpdateClientesDTO updateClientesDTO) {
        clienteRepository.findById(id).map(clienteAtual -> {
            var cliente = clienteRepository.getReferenceById(id);
            cliente.att(updateClientesDTO);
            clienteRepository.save(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) {
        clienteRepository.findById(id).map(cliente -> {
            if (cliente.isAgendado()) {
                throw new AgendamentoException("Cliente não pode ser excluído pois está em um agendamento.");
            }
            clienteRepository.delete(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente findCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }
}
