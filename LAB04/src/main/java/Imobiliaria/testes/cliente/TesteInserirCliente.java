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

        Cliente cliente2 = new Cliente();
        cliente2.setNome("Marcos Pereira");
        cliente2.setCpf("123.236.789-10");
        cliente2.setTelefone("982654321");
        cliente2.setEmail("marcos@example.com");
        cliente2.setDataNascimento(LocalDate.of(1999, 1, 15));

        clienteRepository.salvaOuAtualiza(cliente);
        clienteRepository.salvaOuAtualiza(cliente2);
        System.out.println("Clientes inseridos com sucesso!");

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
