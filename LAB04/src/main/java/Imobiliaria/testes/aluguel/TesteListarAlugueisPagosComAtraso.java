package Imobiliaria.testes.aluguel;

import Imobiliaria.entity.Aluguel;
import Imobiliaria.repository.AluguelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TesteListarAlugueisPagosComAtraso {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        AluguelRepository repository = new AluguelRepository(manager);

        System.out.println("Aluguéis pagos com atraso:");
        repository.listarAlugueisPagosComAtraso();

        manager.close();
        factory.close();
    }
}
