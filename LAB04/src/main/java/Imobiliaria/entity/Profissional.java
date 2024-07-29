package Imobiliaria.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"servicos"})
@EqualsAndHashCode(of = {"id"})
@Entity
public class Profissional implements EntidadeBase {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String profissao;

    @Column(nullable = false)
    private String telefone1;

    private String telefone2;

    private BigDecimal valorHora;

    private String obs;

    @OneToMany(mappedBy = "profissional")
    private Set<ServicoImovel> servicos = new LinkedHashSet<>();

    public void addServico(ServicoImovel servico) {
        servicos.add(servico);
    }
}
