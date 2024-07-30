package Imobiliaria.testes.imovel;

import Imobiliaria.entity.Imovel;
import Imobiliaria.repository.ImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteBuscarImovelPorID {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ImovelRepository repository = new ImovelRepository(manager);

        Imovel imovel = repository.porId(1); // Supondo que o ID 1 exista
        System.out.println(imovel);

        manager.close();
        factory.close();
    }
}
