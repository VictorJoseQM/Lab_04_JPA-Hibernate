package Imobiliaria.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
public class Locacao implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "imovel_id", nullable = false)
    private Imovel imovel;

    @ManyToOne
    @JoinColumn(name = "inquilino_id", nullable = false)
    private Cliente cliente;

    private BigDecimal valorAluguel;

    private BigDecimal percentualMulta;

    @Column(nullable = false)
    private byte diaVencimento;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Column(nullable = false)
    private Boolean ativo;

    private String obs;

    @OneToMany(mappedBy = "locacao")
    private Set<Aluguel> alugueis = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Locacao{" +
                "id=" + id +
                ", imovel=" + imovel.getCep() +
                ", cliente=" + cliente.getNome() +
                '}';
    }
}
