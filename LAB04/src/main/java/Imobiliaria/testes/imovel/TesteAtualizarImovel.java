package Imobiliaria.testes.imovel;

import Imobiliaria.entity.Imovel;
import Imobiliaria.repository.ImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TesteAtualizarImovel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        ImovelRepository repository = new ImovelRepository(manager);

        // Supondo que o ID 1 exista
        Imovel imovel = repository.porId(1);
        System.out.println("Antes da atualização: " + imovel);

        transaction.begin();
        imovel.setValorAluguelSugerido(new BigDecimal("2500.00"));
        imovel.setObs("Atualizado com novo valor de aluguel.");
        repository.salvaOuAtualiza(imovel);
        transaction.commit();

        Imovel imovelAtualizado = repository.porId(1);
        System.out.println("Depois da atualização: " + imovelAtualizado);

        manager.close();
        factory.close();
    }
}
