package Imobiliaria.testes.aluguel;

import Imobiliaria.entity.Aluguel;
import Imobiliaria.entity.Cliente;
import Imobiliaria.repository.AluguelRepository;
import Imobiliaria.repository.ClienteRepository;
import Imobiliaria.service.PagamentoAluguelService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TesteRegistroDePagamentos {
    public static void main(String[] args) {
        // Criação do EntityManager
        EntityManager em = Persistence.createEntityManagerFactory("lab_jpa").createEntityManager();
        PagamentoAluguelService pagamentoAluguelService = new PagamentoAluguelService(em);

        // ID do cliente que você quer buscar
        Integer idCliente = 1; // Exemplo de ID

        // Busca o cliente do banco de dados
        ClienteRepository clienteRepository = new ClienteRepository(em);
        Cliente cliente = clienteRepository.porId(idCliente);

        // Verifica se o cliente foi encontrado
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            em.close();
            return;
        }

        AluguelRepository aluguelRepository = new AluguelRepository(em);

        // Busca os aluguéis do cliente (se houver)
        List<Aluguel> alugueis = aluguelRepository.listarAlugueisPorCliente(cliente.getNome());

        // Obtendo o primeiro aluguel da lista
        Aluguel primeiroAluguel = null;
        if (!alugueis.isEmpty()) {
            primeiroAluguel = alugueis.get(0); // Corrigido para pegar o primeiro elemento da lista
        }

        if (primeiroAluguel != null) {
            // Calcula o valor com multa para um pagamento em atraso
            BigDecimal valorComMulta = pagamentoAluguelService.calcularValorComMulta(primeiroAluguel, primeiroAluguel.getDataPagamento());
            System.out.printf("Valor com multa: %.2f%n", valorComMulta.doubleValue());

            // Registra o pagamento
            pagamentoAluguelService.registrarPagamento(primeiroAluguel, primeiroAluguel.getDataPagamento(), valorComMulta);
        } else {
            System.out.println("Nenhum aluguel encontrado.");
        }

        // Fechar o EntityManager
        em.close();
    }
}
