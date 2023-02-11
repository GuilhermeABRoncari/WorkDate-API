package com.br.workdate.apiworkdate.domain.servicos;

import com.br.workdate.apiworkdate.domain.agendamentos.Agendamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "Servico")
@Table(name = "servicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Servicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id")
    private Long id;
    private String descricao;
    private BigDecimal valor;

    public Servicos(ServicoData data) {
        this.descricao = data.descricao();
        this.valor = data.valor();
    }

    public void att(UpdateServico updateServico) {
        if (updateServico.valor() != null) this.valor = updateServico.valor();
    }
}
