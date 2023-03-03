package com.br.workdate.apiworkdate.rest.dto;

import java.math.BigDecimal;

public record ResumoDTO(String open, String paid, String total) {
    public ResumoDTO(BigDecimal open, BigDecimal paid, BigDecimal total){
        this(open.toString(), paid.toString(), total.toString());
    }
}
