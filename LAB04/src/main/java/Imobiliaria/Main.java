package Imobiliaria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import Imobiliaria.entity.*;
import Imobiliaria.repository.*;
import Imobiliaria.service.PagamentoAluguelService;

public class Main {                                 // Classe para criação e população do banco de dados.
    public static void main(String[] args) {


        // Cria a EntityManagerFactory
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");

        // Cria um EntityManager
        EntityManager em = factory.createEntityManager();

        try {
            // Inicia uma transação
            em.getTransaction().begin();

            // Cria instâncias de repositórios
            ProfissionalRepository profissionalRepository = new ProfissionalRepository(em);
            ImovelRepository imovelRepository = new ImovelRepository(em);
            ClienteRepository clienteRepository = new ClienteRepository(em);
            TipoImovelRepository tipoImovelRepository = new TipoImovelRepository(em);
            LocacaoRepository locacaoRepository = new LocacaoRepository(em);
            ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(em);
            AluguelRepository aluguelRepository = new AluguelRepository(em);

            // Cria instâncias de Profissional
            Profissional profissional1 = new Profissional();
            profissional1.setNome("Maria de Souza");
            profissional1.setProfissao("Engenheira Civil");
            profissional1.setTelefone1("987654321");
            profissional1.setTelefone2("123456789");
            profissional1.setValorHora(new BigDecimal("100.00"));
            profissional1.setObs("Especialista em construções residenciais.");

            Profissional profissional2 = new Profissional();
            profissional2.setNome("Carlos Pereira");
            profissional2.setProfissao("Arquiteto");
            profissional2.setTelefone1("123456789");
            profissional2.setTelefone2("987654321");
            profissional2.setValorHora(new BigDecimal("150.00"));
            profissional2.setObs("Especialista em projetos sustentáveis.");

            // Persiste Profissionais no banco de dados
            profissionalRepository.salvaOuAtualiza(profissional1);
            profissionalRepository.salvaOuAtualiza(profissional2);

            // Cria instância de Cliente
            Cliente cliente = new Cliente();
            cliente.setNome("João Silva");
            cliente.setCpf("12345678900");
            cliente.setEmail("joao.silva@example.com");
            cliente.setTelefone("987654321");
            cliente.setDataNascimento(LocalDate.now());

            // Persiste Cliente no banco de dados
            clienteRepository.salvaOuAtualiza(cliente);

            // Cria instância de TipoImovel
            TipoImovel tipoImovel = new TipoImovel();
            tipoImovel.setDescricao("Apartamento");

            tipoImovelRepository.salvaOuAtualiza(tipoImovel);

            // Cria instâncias de Imovel
            Imovel imovel1 = new Imovel();
            imovel1.setLogradouro("Rua A");
            imovel1.setBairro("Centro");
            imovel1.setCep("12345-678");
            imovel1.setMetragem(100);
            imovel1.setDormitorios((byte) 3);
            imovel1.setBanheiros((byte) 2);
            imovel1.setSuites((byte) 1);
            imovel1.setVagasGaragem((byte) 2);
            imovel1.setValorAluguelSugerido(new BigDecimal("1000.00"));
            imovel1.setObs("Imóvel bem localizado.");
            imovel1.setCliente(cliente); // Define o cliente para o imóvel
            imovel1.setTipoImovel(tipoImovel); // Define o tipo de imóvel

            // Persiste Imovel no banco de dados
            imovelRepository.salvaOuAtualiza(imovel1);

            // Garantir que o ID do imóvel seja gerado
            em.flush();

            // Cria instância de Locacao e associa ao Imovel e Cliente
            Locacao locacao1 = new Locacao();
            locacao1.setImovel(imovel1);
            locacao1.setCliente(cliente);
            locacao1.setValorAluguel(new BigDecimal("2500.00"));
            locacao1.setPercentualMulta(new BigDecimal("2.00"));
            locacao1.setDiaVencimento((byte) 5);
            locacao1.setDataInicio(LocalDate.now());
            locacao1.setDataFim(LocalDate.now().plusYears(1));
            locacao1.setAtivo(false);
            locacao1.setObs("Primeira locação do imóvel.");

            // Tenta persistir a Locacao no banco de dados
            locacaoRepository.salvaOuAtualiza(locacao1);

            // Cria instância de ServicoImovel e associa a Profissional e Imovel
            ServicoImovel servico1 = new ServicoImovel();
            servico1.setProfissional(profissional1);
            servico1.setImovel(imovel1);
            servico1.setDataServico(LocalDate.now());
            servico1.setValorTotal(new BigDecimal("500.00"));
            servico1.setObs("Serviço de pintura");

            ServicoImovel servico2 = new ServicoImovel();
            servico2.setProfissional(profissional2);
            servico2.setImovel(imovel1);
            servico2.setDataServico(LocalDate.now().plusDays(1));
            servico2.setValorTotal(new BigDecimal("700.00"));
            servico2.setObs("Serviço de arquitetura");

            // Persiste os serviços no banco de dados
            servicoImovelRepository.salvaOuAtualiza(servico1);
            servicoImovelRepository.salvaOuAtualiza(servico2);

            // Cria instância de Aluguel e associa a Locacao
            Aluguel aluguel1 = new Aluguel();
            aluguel1.setLocacao(locacao1);
            aluguel1.setDataVencimento(LocalDate.now().plusMonths(1));
            aluguel1.setValorPago(new BigDecimal("2500.00"));
            aluguel1.setDataPagamento(LocalDate.now().plusMonths(1).plusDays(5));
            aluguel1.setObs("Aluguel pago com atraso.");

            Aluguel aluguel2 = new Aluguel();
            aluguel2.setLocacao(locacao1);
            aluguel2.setDataVencimento(LocalDate.now().plusMonths(2));
            aluguel2.setValorPago(new BigDecimal("2500.00"));
            aluguel2.setDataPagamento(LocalDate.now().plusMonths(2).plusDays(3));
            aluguel2.setObs("Aluguel pago com atraso.");

            Aluguel aluguel3 = new Aluguel();
            aluguel3.setLocacao(locacao1);
            aluguel3.setDataVencimento(LocalDate.now().plusMonths(3));
            aluguel3.setValorPago(new BigDecimal("2500.00"));
            aluguel3.setDataPagamento(LocalDate.now().plusMonths(3));
            aluguel3.setObs("Aluguel pago em dia.");

            // Persiste os aluguéis no banco de dados
            aluguelRepository.salvaOuAtualiza(aluguel1);
            aluguelRepository.salvaOuAtualiza(aluguel2);
            aluguelRepository.salvaOuAtualiza(aluguel3);

            // Finaliza a transação
            em.getTransaction().commit();
            System.out.println("\nBanco lab_jpa criado e populado!");

        } catch (Exception e) {
            // Em caso de erro, desfaz a transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Fecha o EntityManager e a EntityManagerFactory
            em.close();
            factory.close();
        }


    }
}