package Imobiliaria.entity;

import javax.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter @ToString(exclude = "imoveis")
@EqualsAndHashCode(exclude = "imoveis")
@Entity
public class TipoImovel implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @OneToMany(mappedBy = "tipoImovel")
    private Set<Imovel> imoveis = new LinkedHashSet<>();

    public void addImovel(Imovel imovel) {
        imoveis.add(imovel);
    }

}
