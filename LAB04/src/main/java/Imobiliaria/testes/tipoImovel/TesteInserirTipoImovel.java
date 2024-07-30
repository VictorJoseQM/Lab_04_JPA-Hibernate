package Imobiliaria.testes.tipoImovel;

import Imobiliaria.entity.TipoImovel;
import Imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteInserirTipoImovel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        TipoImovelRepository tipoImovelRepo = new TipoImovelRepository(manager);

        manager.getTransaction().begin();

        TipoImovel tipoImovel1 = new TipoImovel();
        tipoImovel1.setDescricao("Apartamento");

        TipoImovel tipoImovel2 = new TipoImovel();
        tipoImovel2.setDescricao("Casa");

        TipoImovel tipoImovel3 = new TipoImovel();
        tipoImovel3.setDescricao("Comercial");

        tipoImovelRepo.salvaOuAtualiza(tipoImovel1);
        tipoImovelRepo.salvaOuAtualiza(tipoImovel2);
        tipoImovelRepo.salvaOuAtualiza(tipoImovel3);

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
