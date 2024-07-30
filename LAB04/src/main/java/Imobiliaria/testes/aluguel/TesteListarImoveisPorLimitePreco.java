package Imobiliaria.testes.aluguel;

import Imobiliaria.repository.AluguelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TesteListarImoveisPorLimitePreco {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        AluguelRepository repository = new AluguelRepository(manager);

        BigDecimal limitePreco = new BigDecimal("1400.00");
        System.out.println("Imóveis com valor de aluguel sugerido até " + limitePreco + ":");
        repository.listarImoveisPorLimitePreco(limitePreco);

        manager.close();
        factory.close();
    }
}
