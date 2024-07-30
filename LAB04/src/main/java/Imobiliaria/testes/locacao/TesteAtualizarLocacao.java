package Imobiliaria.testes.locacao;

import Imobiliaria.entity.Locacao;
import Imobiliaria.repository.LocacaoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TesteAtualizarLocacao {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        LocacaoRepository locacaoRepository = new LocacaoRepository(manager);

        try {
            manager.getTransaction().begin();

            // Buscar uma locação existente
            Locacao locacao = locacaoRepository.porId(1); // Substitua 1 pelo ID da locação que você deseja atualizar

            if (locacao != null) {
                // Atualizar os dados da locação
                locacao.setValorAluguel(locacao.getValorAluguel().add(new BigDecimal("100"))); // Exemplo de atualização: adicionar R$100 ao valor do aluguel
                locacao.setObs("Atualização de teste");
                locacao.setAtivo(true);

                // Salvar as alterações
                locacaoRepository.salvaOuAtualiza(locacao);
                System.out.println("Locação atualizada com sucesso!");
            } else {
                System.out.println("Locação não encontrada.");
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        } finally {
            manager.close();
            factory.close();
        }
    }
}
