package Imobiliaria.testes.servicoImovel;

import Imobiliaria.entity.ServicoImovel;
import Imobiliaria.entity.Profissional;
import Imobiliaria.entity.Imovel;
import Imobiliaria.repository.ServicoImovelRepository;
import Imobiliaria.repository.ProfissionalRepository;
import Imobiliaria.repository.ImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteAtualizarServicoImovel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(manager);
        ProfissionalRepository profissionalRepository = new ProfissionalRepository(manager);
        ImovelRepository imovelRepository = new ImovelRepository(manager);

        try {
            // ID do serviço de imóvel a ser atualizado
            Integer id = 1; // Substitua com o ID do serviço de imóvel a ser atualizado

            // Inicia uma transação
            manager.getTransaction().begin();

            // Buscar o serviço de imóvel existente
            ServicoImovel servicoImovel = servicoImovelRepository.porId(id);

            if (servicoImovel != null) {
                // Atualizar informações do serviço de imóvel
                Profissional novoProfissional = profissionalRepository.porId(2); // Substitua com um ID válido
                Imovel novoImovel = imovelRepository.porId(3); // Substitua com um ID válido

                servicoImovel.setProfissional(novoProfissional);
                servicoImovel.setImovel(novoImovel);
                servicoImovel.setDataServico(LocalDate.now());
                servicoImovel.setValorTotal(new BigDecimal("1500.00"));
                servicoImovel.setObs("Atualização do serviço de imóvel.");

                // Salvar as alterações
                servicoImovelRepository.salvaOuAtualiza(servicoImovel);
                System.out.println("Serviço de Imóvel atualizado com sucesso!");

                // Confirmar a transação
                manager.getTransaction().commit();
            } else {
                System.out.println("Nenhum serviço de imóvel encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback(); // Reverter a transação em caso de erro
            }
        } finally {
            manager.close();
            factory.close();
        }
    }
}
