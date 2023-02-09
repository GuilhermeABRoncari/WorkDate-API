package com.br.workdate.apiworkdate.servicos;

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
}
