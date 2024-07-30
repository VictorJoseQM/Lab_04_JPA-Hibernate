package Imobiliaria.testes.imovel;

import Imobiliaria.repository.ImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteListarImoveis {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ImovelRepository imovelRepo = new ImovelRepository(manager);

        System.out.println("Listando todos os imóveis:");
        imovelRepo.listarImoveis();

        manager.close();
        factory.close();
    }
}
