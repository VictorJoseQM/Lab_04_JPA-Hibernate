package Imobiliaria.testes.profissional;

import Imobiliaria.entity.Profissional;
import Imobiliaria.repository.ProfissionalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TesteAtualizarProfissional {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ProfissionalRepository repository = new ProfissionalRepository(manager);

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Profissional profissional = repository.porId(1); // Assumindo que o ID do profissional existente é 1
        if (profissional != null) {
            profissional.setValorHora(new BigDecimal("60.00"));
            repository.salvaOuAtualiza(profissional);
            System.out.println("Profissional atualizado com sucesso!");
        } else {
            System.out.println("Profissional não encontrado!");
        }

        transaction.commit();
        manager.close();
        factory.close();
    }
}
