package com.br.workdate.apiworkdate.domain.repository;

import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;
import com.br.workdate.apiworkdate.domain.Situation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Long> {
    List<LancamentoFinanceiro> findAllBySituation(Situation situation);

    LancamentoFinanceiro findByAgendamentoId(Long id);
}
