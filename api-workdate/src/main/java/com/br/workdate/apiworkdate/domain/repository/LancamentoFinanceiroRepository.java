package com.br.workdate.apiworkdate.domain.repository;

import com.br.workdate.apiworkdate.domain.entity.LancamentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoFinanceiroRepository extends JpaRepository<LancamentoFinanceiro, Long> {
}
