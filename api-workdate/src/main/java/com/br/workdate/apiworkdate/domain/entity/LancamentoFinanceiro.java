package com.br.workdate.apiworkdate.domain.entity;

import com.br.workdate.apiworkdate.domain.Situation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "lancamento_financeiro")
@Table(name = "lancamentos_financeiros")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoFinanceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lancamentos_financeiros_id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;
    @Enumerated(EnumType.STRING)
    @Column(name = "situation")
    private Situation situation;

    public void situationChange(Situation situation){
        this.situation = situation;
    }
}
