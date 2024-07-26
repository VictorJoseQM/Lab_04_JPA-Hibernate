package com.lab1.imobiliaria.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public @Data class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_locacao")
    private Locacao locacao;

    @Column private Date dataVencimento;
    @Column private BigDecimal valorPago;
    @Column private Date dataPagamento;
    @Column private String obs;

}
