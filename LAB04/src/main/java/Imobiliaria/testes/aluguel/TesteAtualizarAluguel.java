package Imobiliaria.testes.aluguel;

import Imobiliaria.entity.Aluguel;
import Imobiliaria.repository.AluguelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteAtualizarAluguel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();
        AluguelRepository aluguelRepository = new AluguelRepository(manager);

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Aluguel aluguel = aluguelRepository.porId(1); // Assumindo que o aluguel com ID 1 já existe
        aluguel.setValorPago(new BigDecimal("1050.00"));
        aluguel.setDataPagamento(LocalDate.now());

        aluguelRepository.salvaOuAtualiza(aluguel);

        transaction.commit();

        System.out.println("Aluguel atualizado com sucesso: " + aluguel);

        manager.close();
        factory.close();
    }
}
