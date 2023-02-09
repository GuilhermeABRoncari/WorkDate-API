package com.br.workdate.apiworkdate.controller;

import com.br.workdate.apiworkdate.clientes.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public void addCliente(@RequestBody @Valid DataClientes data) {
        repository.save(new Cliente(data));
    }

    @GetMapping
    public Page<DataClienteList> listCliente(@PageableDefault(sort = {"nome"}) Pageable pageable) {
        return repository.findAll(pageable).map(DataClienteList::new);
    }

    @PutMapping
    @Transactional
    public void attCliente(@RequestBody @Valid UpdateClientes data) {
        var cliente = repository.getReferenceById(data.id());
        cliente.att(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCliente(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
