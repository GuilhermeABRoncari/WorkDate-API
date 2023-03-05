package com.br.workdate.apiworkdate.domain.entity;

import com.br.workdate.apiworkdate.rest.dto.ClienteDTO;
import com.br.workdate.apiworkdate.rest.dto.UpdateClientesDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Entity(name = "Cliente")
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Long id;
    private String nome;
    private String endereco;
    private String fone;
    private boolean agendado = false;

    public Cliente(ClienteDTO data) {
        this.nome = data.nome();
        this.endereco = data.endereco();
        this.fone = data.fone();
    }

    @Bean
    public void att(UpdateClientesDTO data) {
        if (data.nome() != null) this.nome = data.nome();
        if (data.endereco() != null) this.endereco = data.endereco();
        if (data.fone() != null) this.fone = data.fone();
    }
}
