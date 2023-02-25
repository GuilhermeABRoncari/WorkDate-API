package com.br.workdate.apiworkdate.domain.repository;

import com.br.workdate.apiworkdate.domain.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
