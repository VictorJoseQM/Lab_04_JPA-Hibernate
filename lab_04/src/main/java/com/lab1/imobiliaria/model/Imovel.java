package com.lab1.imobiliaria.model;

import antlr.collections.List;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public @Data class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_proprietario")
    private Cliente proprietario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_imovel")
    private TipoImovel tipoImovel;

    @Column private String logradouro;
    @Column private String bairro;
    @Column private String cep;
    @Column private Integer metragem;
    @Column private Integer dormitorios;
    @Column private Integer banheiros;
    @Column private Integer suites;
    @Column private Integer vagas;
    @Column private BigDecimal valorAluguelSugerido;
    @Column private String obs;

    @OneToMany(mappedBy = "imovel")
    private Set<Locacao> locacoes;

    @OneToMany(mappedBy = "imovel")
    private Set<ServicoImovel> servicos;

}
