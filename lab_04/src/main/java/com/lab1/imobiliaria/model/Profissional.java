package com.lab1.imobiliaria.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public @Data class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Integer id;
    @Column private String nome;
    @Column private String profissao;
    @Column private String telefone1;
    @Column private String telefone2;
    @Column private BigDecimal valorHora;
    @Column private String obs;

    @OneToMany(mappedBy = "profissional")
    private Set<ServicoImovel> servicos;

}
