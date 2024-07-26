package com.lab1.imobiliaria.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
public @Data class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_imovel")
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "id_inquilino")
    private Cliente inquilino;

    @Column private BigDecimal valorAluguel;
    @Column private BigDecimal percentualMulta;
    @Column private Integer diaVencimento;
    @Column private Date dataInicio;
    @Column private Date dataFim;
    @Column private Boolean ativo;
    @Column private String obs;

    @OneToMany(mappedBy = "locacao")
    private Set<Aluguel> alugueis;
}
