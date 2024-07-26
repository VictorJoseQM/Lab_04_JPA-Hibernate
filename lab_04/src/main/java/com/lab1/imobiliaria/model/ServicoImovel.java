package com.lab1.imobiliaria.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public @Data class ServicoImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_profissional")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "id_imovel")
    private Imovel imovel;

    @Column private Date dataServico;
    @Column private BigDecimal valorTotal;
    @Column private String obs;

}
