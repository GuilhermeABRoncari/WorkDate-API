package com.br.workdate.apiworkdate.domain.entity;

import com.br.workdate.apiworkdate.infra.Situation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoFinanceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;
    @Enumerated(EnumType.STRING)
    private Situation situation;

    public void situationChange(Situation situation){
        this.situation = situation;
    }
}
