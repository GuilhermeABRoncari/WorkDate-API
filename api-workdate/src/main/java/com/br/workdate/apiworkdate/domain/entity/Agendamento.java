package com.br.workdate.apiworkdate.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity(name = "Agendamento")
@Table(name = "agendamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agendamento_id")
    private Long id;
    private Timestamp horario;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servico_id")
    private Servico servico;
    private String observacoes;
    private Boolean concluido;
    private Boolean cancelado;

    public void concluir(){
        this.concluido = true;
    }
    public void cancelar(){
        this.cancelado = true;
    }

    public void update(Cliente cliente, Servico servico, Timestamp horario) {
        this.cliente = cliente;
        this.servico = servico;
        this.horario = horario;
    }
}
