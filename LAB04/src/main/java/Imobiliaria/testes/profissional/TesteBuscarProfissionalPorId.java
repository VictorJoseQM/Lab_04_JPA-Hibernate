package Imobiliaria.testes.profissional;

import Imobiliaria.entity.Profissional;
import Imobiliaria.repository.ProfissionalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteBuscarProfissionalPorId {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ProfissionalRepository repository = new ProfissionalRepository(manager);

        int profissionalId = 1; // ID do profissional que você deseja buscar

        Profissional profissional = repository.porId(profissionalId);
        if (profissional != null) {
            System.out.println(profissional.toString());
        } else {
            System.out.println("Profissional com ID " + profissionalId + " não encontrado.");
        }

        manager.close();
        factory.close();
    }
}
