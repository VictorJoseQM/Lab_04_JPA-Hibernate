package Imobiliaria.testes.cliente;

import Imobiliaria.entity.Cliente;
import Imobiliaria.repository.ClienteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteAtualizarCliente {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ClienteRepository clienteRepository = new ClienteRepository(manager);

        manager.getTransaction().begin();

        Cliente cliente = clienteRepository.porId(1);
        if (cliente != null) {
            cliente.setTelefone("123456789");
            clienteRepository.salvaOuAtualiza(cliente);
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
