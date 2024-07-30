package Imobiliaria.testes.aluguel;

import Imobiliaria.entity.Aluguel;
import Imobiliaria.repository.AluguelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteBuscarAluguelPorId {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();
        AluguelRepository aluguelRepository = new AluguelRepository(manager);

        Aluguel aluguel = aluguelRepository.porId(1); // Assumindo que o aluguel com ID 1 já existe
        System.out.println("Aluguel buscado por ID: " + aluguel);

        manager.close();
        factory.close();
    }
}
