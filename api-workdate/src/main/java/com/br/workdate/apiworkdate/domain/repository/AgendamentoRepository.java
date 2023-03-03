package com.br.workdate.apiworkdate.domain.repository;

import com.br.workdate.apiworkdate.domain.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findAllByConcluidoTrue();

    List<Agendamento> findAllByCanceladoTrue();
}
