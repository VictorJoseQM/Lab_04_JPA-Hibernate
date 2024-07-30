package Imobiliaria.testes.locacao;

import Imobiliaria.entity.Cliente;
import Imobiliaria.entity.Imovel;
import Imobiliaria.entity.Locacao;
import Imobiliaria.repository.ClienteRepository;
import Imobiliaria.repository.ImovelRepository;
import Imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteInserirLocacao {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);
        ImovelRepository imovelRepository = new ImovelRepository(manager);
        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);

        manager.getTransaction().begin();

        // Buscar um cliente e um imóvel existentes
        Cliente cliente = clienteRepository.porId(1); // Substitua pelo ID real
        Imovel imovel = imovelRepository.porId(1); // Substitua pelo ID real

        if (cliente == null || imovel == null) {
            System.out.println("Cliente ou imóvel não encontrado.");
            manager.getTransaction().rollback();
            manager.close();
            factory.close();
            return;
        }

        // Criar uma nova locação
        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setImovel(imovel);
        locacao.setValorAluguel(new BigDecimal("1200.00"));
        locacao.setPercentualMulta(new BigDecimal("0.05"));
        locacao.setDiaVencimento((byte) 5);
        locacao.setDataInicio(LocalDate.now());
        locacao.setDataFim(LocalDate.now().plusMonths(12));
        locacao.setAtivo(true);
        locacao.setObs("Teste de inserção");

        locacaoRepository.salvaOuAtualiza(locacao);
        System.out.println("Locação inserida com sucesso!");

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
