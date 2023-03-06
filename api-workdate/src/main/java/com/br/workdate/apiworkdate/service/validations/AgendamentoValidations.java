package com.br.workdate.apiworkdate.service.validations;

import com.br.workdate.apiworkdate.domain.repository.ClienteRepository;
import com.br.workdate.apiworkdate.domain.repository.ServicoRepository;
import com.br.workdate.apiworkdate.infra.AgendamentoException;
import com.br.workdate.apiworkdate.rest.dto.AgendamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoValidations implements WorkDateValidations{
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    private static final String ID_CLIENT = "Id do cliente não encontrado.";
    private static final String ID_SERVICE = "Id do serviço não encontrado.";
    public void isValid(AgendamentoDTO agendamentoDTO){
        var clientExists = clienteRepository.existsById(agendamentoDTO.cliente_id());
        if(!clientExists) throw new AgendamentoException(ID_CLIENT);

        var serviceExists = servicoRepository.existsById(agendamentoDTO.servico_id());
        if(!serviceExists) throw new AgendamentoException(ID_SERVICE);
    }
}
