package Imobiliaria.testes.profissional;

import Imobiliaria.entity.Profissional;
import Imobiliaria.repository.ProfissionalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TesteInserirProfissional {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ProfissionalRepository repository = new ProfissionalRepository(manager);

        Profissional profissional = new Profissional();
        profissional.setNome("João da Silva");
        profissional.setProfissao("Eletricista");
        profissional.setTelefone1("123456789");
        profissional.setTelefone2("987654321");
        profissional.setValorHora(new BigDecimal("50.00"));
        profissional.setObs("Disponível para serviços de emergência");

        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        repository.salvaOuAtualiza(profissional);
        transaction.commit();

        System.out.println("Profissional inserido com sucesso!");

        manager.close();
        factory.close();
    }
}
