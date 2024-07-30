package Imobiliaria.testes.servicoImovel;

import Imobiliaria.entity.ServicoImovel;
import Imobiliaria.repository.ServicoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TesteListarServicoImovel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(manager);

        try {
            // Buscar todos os serviços de imóveis
            List<ServicoImovel> servicos = servicoImovelRepository.todos();

            System.out.println("Lista de Serviços de Imóveis:");
            for (ServicoImovel servico : servicos) {
                System.out.println(servico);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
            factory.close();
        }
    }
}
