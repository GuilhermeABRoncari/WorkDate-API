package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Servico;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServicoDTO(
        @NotBlank
        String descricao,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BigDecimal valor) {
        public ServicoDTO(Servico servico){
                this(servico.getDescricao(), servico.getValor());
        }
}
