package Imobiliaria;

import Imobiliaria.entity.Cliente;
import Imobiliaria.entity.Imovel;
import Imobiliaria.entity.Locacao;
import Imobiliaria.entity.Profissional;
import Imobiliaria.entity.ServicoImovel;
import Imobiliaria.entity.TipoImovel;
import Imobiliaria.repository.ClienteRepository;
import Imobiliaria.repository.ImovelRepository;
import Imobiliaria.repository.LocacaoRepository;
import Imobiliaria.repository.ProfissionalRepository;
import Imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

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
            imovel1.setValorAluguelSugerido(new BigDecimal("2000.00"));
            imovel1.setObs("Imóvel bem localizado.");
            imovel1.setCliente(cliente); // Define o cliente para o imóvel
            imovel1.setTipoImovel(tipoImovel); // Define o tipo de imóvel

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

            // Adiciona os serviços ao Profissional e ao Imovel
            profissional1.addServico(servico1);
            profissional2.addServico(servico2);
            imovel1.addServico(servico1);
            imovel1.addServico(servico2);

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
            locacao1.setAtivo(true);
            locacao1.setObs("Primeira locação do imóvel.");

            Locacao locacao2 = new Locacao();
            locacao2.setImovel(imovel1);
            locacao2.setCliente(cliente);
            locacao2.setValorAluguel(new BigDecimal("2500.00"));
            locacao2.setPercentualMulta(new BigDecimal("2.00"));
            locacao2.setDiaVencimento((byte) 5);
            locacao2.setDataInicio(LocalDate.now());
            locacao2.setDataFim(LocalDate.now().plusYears(2));
            locacao2.setAtivo(true);
            locacao2.setObs("Segunda locação do imóvel.");

            // Tenta persistir a Locacao no banco de dados
            try {
                locacaoRepository.salvaOuAtualiza(locacao1);
                locacaoRepository.salvaOuAtualiza(locacao2);
                System.out.println("Locação registrada com sucesso!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao registrar locação: " + e.getMessage());
            }

            // Finaliza a transação
            em.getTransaction().commit();

            // Lista todos os profissionais cadastrados
            profissionalRepository.listarProfissionais();

            // Lista todos os imóveis cadastrados
            imovelRepository.listarImoveis();

            // Lista todas as locações do cliente
            locacaoRepository.listarLocacoesPorCliente(cliente.getId());

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
