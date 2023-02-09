package com.br.workdate.apiworkdate.servicos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateServico(
        @NotNull
        Long id,
        @NotNull
        BigDecimal valor) {
        public UpdateServico(Servicos servico){
                this(servico.getId(), servico.getValor());
        }
}
