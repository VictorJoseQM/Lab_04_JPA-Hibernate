package Imobiliaria.testes.imovel;

import Imobiliaria.entity.Cliente;
import Imobiliaria.entity.Imovel;
import Imobiliaria.entity.TipoImovel;
import Imobiliaria.repository.ClienteRepository;
import Imobiliaria.repository.ImovelRepository;
import Imobiliaria.repository.TipoImovelRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class TesteInserirImovel {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("lab_jpa");
        EntityManager manager = factory.createEntityManager();

        ClienteRepository clienteRepo = new ClienteRepository(manager);
        TipoImovelRepository tipoImovelRepo = new TipoImovelRepository(manager);
        ImovelRepository imovelRepo = new ImovelRepository(manager);

        manager.getTransaction().begin();

        Cliente cliente = clienteRepo.porId(1);  // Assumindo que há um cliente com ID 1
        TipoImovel tipoImovel = tipoImovelRepo.porId(1);  // Assumindo que há um tipo de imóvel com ID 1

        Imovel imovel = new Imovel();
        imovel.setCliente(cliente);
        imovel.setTipoImovel(tipoImovel);
        imovel.setLogradouro("Rua da Esperança");
        imovel.setBairro("Centro");
        imovel.setCep("12345-678");
        imovel.setMetragem(120);
        imovel.setDormitorios((byte) 3);
        imovel.setBanheiros((byte) 2);
        imovel.setSuites((byte) 1);
        imovel.setVagasGaragem((byte) 2);
        imovel.setValorAluguelSugerido(new BigDecimal("1500.00"));
        imovel.setObs("Imóvel recém reformado.");

        imovelRepo.salvaOuAtualiza(imovel);

        manager.getTransaction().commit();

        manager.close();
        factory.close();
    }
}
