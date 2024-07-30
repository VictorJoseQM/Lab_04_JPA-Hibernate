package Imobiliaria.testes.locacao;

import Imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteListarLocacaoPorCliente {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);

        try {
            locacaoRepository.listarLocacoesPorCliente(1);
        } finally {
            manager.close();
            factory.close();
        }
    }
}
