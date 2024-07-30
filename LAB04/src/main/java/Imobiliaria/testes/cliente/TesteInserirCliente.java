package Imobiliaria.testes.cliente;

import Imobiliaria.entity.Cliente;
import Imobiliaria.repository.ClienteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class TesteInserirCliente {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);

        manager.getTransaction().begin();

        Cliente cliente = new Cliente();
        cliente.setNome("João da Silva");
        cliente.setCpf("123.456.789-00");
        cliente.setTelefone("987654321");
        cliente.setEmail("joao.silva@example.com");
        cliente.setDataNascimento(LocalDate.of(1985, 1, 15));

        clienteRepository.salvaOuAtualiza(cliente);
        System.out.println("Cliente inserido com sucesso!");

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
