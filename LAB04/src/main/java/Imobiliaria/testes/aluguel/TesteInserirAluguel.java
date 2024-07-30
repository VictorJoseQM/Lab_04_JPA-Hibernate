package Imobiliaria.testes.aluguel;

import Imobiliaria.entity.Aluguel;
import Imobiliaria.entity.Locacao;
import Imobiliaria.repository.AluguelRepository;
import Imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteInserirAluguel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();
        AluguelRepository aluguelRepository = new AluguelRepository(manager);
        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Locacao locacao = locacaoRepository.porId(1); // Assumindo que a locação com ID 1 já existe

        Aluguel aluguel = new Aluguel();
        aluguel.setLocacao(locacao);
        aluguel.setDataVencimento(LocalDate.now().plusMonths(1));
        aluguel.setDataPagamento(LocalDate.now().plusMonths(2));
        aluguel.setObs("Pago com atraso");

        aluguelRepository.salvaOuAtualiza(aluguel);

        transaction.commit();

        System.out.println("Aluguel inserido com sucesso: " + aluguel);

        manager.close();
        factory.close();
    }
}
