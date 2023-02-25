package com.br.workdate.apiworkdate.domain.repository;

import com.br.workdate.apiworkdate.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
