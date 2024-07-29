package Imobiliaria.entity;

import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter @Setter @ToString(exclude = {"locacaos", "servicos", "cliente"})
@EqualsAndHashCode(exclude = {"locacaos", "servicos"})
@Entity
public class Imovel implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tipoImovel_id", nullable = false)
    private TipoImovel tipoImovel;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cep;

    private Integer metragem;

    private byte dormitorios;

    private byte banheiros;

    private byte suites;

    private byte vagasGaragem;

    private BigDecimal valorAluguelSugerido;

    private String obs;

    @OneToMany(mappedBy = "imovel")
    private Set<Locacao> locacaos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "imovel")
    private Set<ServicoImovel> servicos = new LinkedHashSet<>();

    public void addLocacao(Locacao locacao) {
        locacaos.add(locacao);
    }

    public void addServico(ServicoImovel servico) {
        servicos.add(servico);
    }
}
