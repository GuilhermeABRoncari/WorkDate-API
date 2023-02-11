package com.br.workdate.apiworkdate.domain.agendamentos;

import com.br.workdate.apiworkdate.domain.clientes.Cliente;
import com.br.workdate.apiworkdate.domain.servicos.Servicos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity(name = "Agendamento")
@Table(name = "agendamentos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp horario;
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToOne
    @JoinColumn(name = "servico_id")
    private Servicos servico;

    public Agendamento(AgendamentoData data) {
        this.cliente = data.cliente();
        this.servico = data.servico();
        this.horario = data.horario();
    }
}
