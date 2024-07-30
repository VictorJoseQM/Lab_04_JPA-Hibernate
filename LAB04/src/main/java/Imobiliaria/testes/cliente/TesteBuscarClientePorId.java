package Imobiliaria.testes.cliente;

import Imobiliaria.entity.Cliente;
import Imobiliaria.repository.ClienteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteBuscarClientePorId {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);

        Cliente cliente = clienteRepository.porId(1);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }

        manager.close();
        factory.close();
    }
}
