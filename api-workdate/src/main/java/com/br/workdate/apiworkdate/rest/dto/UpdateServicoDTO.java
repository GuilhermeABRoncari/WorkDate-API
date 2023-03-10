package com.br.workdate.apiworkdate.rest.dto;

import com.br.workdate.apiworkdate.domain.entity.Servico;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateServicoDTO(
        @NotNull(message = "{message-09}")
        BigDecimal valor
) {
    public UpdateServicoDTO(Servico servico){
        this(servico.getValor());
    }
}
