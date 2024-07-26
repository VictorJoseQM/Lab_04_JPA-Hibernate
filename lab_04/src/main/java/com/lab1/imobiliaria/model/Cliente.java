package com.lab1.imobiliaria.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public @Data class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Integer id;
    @Column private String nome;
    @Column private String cpf;
    @Column private String telefone;
    @Column private String email;
    @Column private Date dt_Nascimento;

    @OneToMany(mappedBy = "inquilino")
    private Set<Locacao> locacoes;

    @OneToMany(mappedBy = "proprietario")
    private Set<Imovel> imoveis;
}
