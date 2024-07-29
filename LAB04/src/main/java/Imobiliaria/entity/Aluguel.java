package Imobiliaria.entity;

import javax.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data @Entity
public class Aluguel implements EntidadeBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "locacao_id", nullable = false)
    private Locacao locacao;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    private BigDecimal valorPago;

    private LocalDate dataPagamento;

    private String obs;
}
