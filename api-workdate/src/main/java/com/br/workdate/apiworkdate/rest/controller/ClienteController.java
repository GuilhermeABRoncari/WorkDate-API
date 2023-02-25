package com.br.workdate.apiworkdate.rest.controller;

import com.br.workdate.apiworkdate.domain.clientes.DataClienteList;
import com.br.workdate.apiworkdate.domain.clientes.UpdateClientes;
import com.br.workdate.apiworkdate.domain.entity.Cliente;
import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.rest.dto.ClienteDTO;
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

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        var cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DataClienteList> listCliente(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        var page = clienteRepository.findAll(pageable).map(DataClienteList::new);
        return page;
    }

    @PutMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public Cliente attCliente(@RequestBody @Valid UpdateClientes data) {
        var cliente = clienteRepository.getReferenceById(data.id());
        cliente.att(data);
        return cliente;
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id) {
        clienteRepository.findById(id).map(cliente -> {
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
