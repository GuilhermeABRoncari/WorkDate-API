package com.br.workdate.apiworkdate.service.validations;

import com.br.workdate.apiworkdate.rest.dto.AgendamentoDTO;

public interface WorkDateValidations {
    void isValid(AgendamentoDTO agendamentoDTO);
}
