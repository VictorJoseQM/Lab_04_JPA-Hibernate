package Imobiliaria.testes.profissional;

import Imobiliaria.repository.ProfissionalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteListarProfissionais {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ProfissionalRepository repository = new ProfissionalRepository(manager);

        System.out.println("Listando todos os profissionais:");
        repository.listarProfissionais();

        manager.close();
        factory.close();
    }
}