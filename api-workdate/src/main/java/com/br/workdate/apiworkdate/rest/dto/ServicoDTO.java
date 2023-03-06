package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Servico;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServicoDTO(
        @NotBlank(message = "{message-07}")
        String descricao,
        @NotNull(message = "{message-08}")
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal valor) {
        public ServicoDTO(Servico servico){
                this(servico.getDescricao(), servico.getValor());
        }
}
