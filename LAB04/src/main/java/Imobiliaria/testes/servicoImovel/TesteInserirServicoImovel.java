package Imobiliaria.testes.servicoImovel;

import Imobiliaria.entity.Imovel;
import Imobiliaria.entity.Profissional;
import Imobiliaria.entity.ServicoImovel;
import Imobiliaria.repository.ImovelRepository;
import Imobiliaria.repository.ProfissionalRepository;
import Imobiliaria.repository.ServicoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TesteInserirServicoImovel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        // Inicializando os repositórios
        ImovelRepository imovelRepository = new ImovelRepository(manager);
        ProfissionalRepository profissionalRepository = new ProfissionalRepository(manager);
        ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(manager);

        manager.getTransaction().begin();

        try {
            // Substitua 1 pelos IDs reais existentes
            Integer imovelId = 1;
            Integer profissionalId = 1;

            // Buscar o imóvel e o profissional pelo ID
            Imovel imovel = imovelRepository.porId(imovelId);
            Profissional profissional = profissionalRepository.porId(profissionalId);

            if (imovel == null || profissional == null) {
                System.out.println("Imóvel ou Profissional não encontrado. Verifique os IDs fornecidos.");
                manager.getTransaction().rollback();
                return;
            }

            // Criar e configurar o novo Serviço
            ServicoImovel servicoImovel = new ServicoImovel();
            servicoImovel.setImovel(imovel);
            servicoImovel.setProfissional(profissional);
            servicoImovel.setDataServico(LocalDate.now());  // Data de hoje como exemplo
            servicoImovel.setValorTotal(BigDecimal.valueOf(500.00)); // Valor total do serviço
            servicoImovel.setObs("Serviço de manutenção geral.");

            // Salvar o serviço no banco de dados
            servicoImovelRepository.salvaOuAtualiza(servicoImovel);
            System.out.println("Serviço inserido com sucesso!");

            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            manager.getTransaction().rollback();
        } finally {
            manager.close();
            factory.close();
        }
    }
}
