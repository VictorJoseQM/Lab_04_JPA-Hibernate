package Imobiliaria.testes.locacao;

import Imobiliaria.entity.Locacao;
import Imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteBuscarLocacaoPorId {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);

        try {
            // Substitua 1 pelo ID da locação que você deseja buscar
            Integer locacaoId = 1;

            // Buscar a locação pelo ID
            Locacao locacao = locacaoRepository.porId(locacaoId);

            if (locacao != null) {
                System.out.println("Locação encontrada:");
                System.out.println(locacao);
            } else {
                System.out.println("Locação com ID " + locacaoId + " não encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
            factory.close();
        }
    }
}
