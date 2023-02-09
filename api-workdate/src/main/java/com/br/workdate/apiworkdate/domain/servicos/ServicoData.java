package com.br.workdate.apiworkdate.domain.servicos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServicoData(
        @NotBlank
        String descricao,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal valor) {
        public ServicoData(Servicos servico){
                this(servico.getDescricao(), servico.getValor());
        }
}
