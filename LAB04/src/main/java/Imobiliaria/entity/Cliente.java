package Imobiliaria.entity;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter @ToString(exclude = "imoveis")
@EqualsAndHashCode(exclude = "imoveis")
@Entity
public class Cliente implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    private String email;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private Set<Imovel> imoveis = new LinkedHashSet<>();

    public void addImovel(Imovel imovel) {
        imoveis.add(imovel);
    }
}
