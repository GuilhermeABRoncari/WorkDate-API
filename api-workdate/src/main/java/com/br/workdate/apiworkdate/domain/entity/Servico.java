package com.br.workdate.apiworkdate.domain.entity;

import com.br.workdate.apiworkdate.rest.dto.ServicoDTO;
import com.br.workdate.apiworkdate.rest.dto.UpdateServicoDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@Entity(name = "Servico")
@Table(name = "servicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_id")
    private Long id;
    private String descricao;
    private BigDecimal valor;

    public Servico(ServicoDTO data) {
        this.descricao = data.descricao();
        this.valor = data.valor();
    }

    @Bean
    public void att(UpdateServicoDTO updateServicoDTO) {
        if (updateServicoDTO.valor() != null) this.valor = updateServicoDTO.valor();
    }
}
