package com.lab1.imobiliaria.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
public @Data class TipoImovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Integer id;
    @Column private String descricao;
    @Column private Integer imovel;

    @OneToMany(mappedBy = "tipoImovel")
    private Set<Imovel> imoveis;
}
