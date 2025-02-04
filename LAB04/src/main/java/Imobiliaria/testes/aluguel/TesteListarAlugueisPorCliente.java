package Imobiliaria.testes.aluguel;

import Imobiliaria.entity.Aluguel;
import Imobiliaria.repository.AluguelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TesteListarAlugueisPorCliente {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        AluguelRepository repository = new AluguelRepository(manager);

        // Levando em consideração que operações anteriores já foram feitas, para o cliente abaixo.
        List<Aluguel> alugueis = repository.listarAlugueisPorCliente("João da Silva");

        for (Aluguel aluguel : alugueis) {
            System.out.println(aluguel);
        }

        manager.close();
        factory.close();
    }
}
