package Imobiliaria.testes.servicoImovel;

import Imobiliaria.entity.ServicoImovel;
import Imobiliaria.repository.ServicoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteBuscarServicoImovelPorID {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(manager);

        try {
            // ID do serviço de imóvel a ser buscado
            Integer id = 1; // Substitua com o ID que deseja buscar

            // Buscar o serviço de imóvel pelo ID
            ServicoImovel servicoImovel = servicoImovelRepository.porId(id);

            if (servicoImovel != null) {
                System.out.println("Serviço de Imóvel encontrado:");
                System.out.println(servicoImovel);
            } else {
                System.out.println("Nenhum serviço de imóvel encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
            factory.close();
        }
    }
}
