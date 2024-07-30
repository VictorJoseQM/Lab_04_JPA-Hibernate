package Imobiliaria.testes.tipoImovel;

import Imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteListarTiposImovel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        TipoImovelRepository tipoImovelRepo = new TipoImovelRepository(manager);

        System.out.println("Listando todos os tipos de imóveis:");
        tipoImovelRepo.listarTiposImovel();

        manager.close();
        factory.close();
    }
}
