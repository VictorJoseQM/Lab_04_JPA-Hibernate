package Imobiliaria.repository;

import Imobiliaria.entity.Aluguel;
import Imobiliaria.entity.Imovel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class AluguelRepository {

    private final EntityManager em;
    private final DAOGenerico<Aluguel> dao;

    public AluguelRepository(EntityManager em) {
        this.em = em;
        this.dao = new DAOGenerico<>(em);
    }

    public void salvaOuAtualiza(Aluguel aluguel) {
        if (aluguel.getId() == null) {
            em.persist(aluguel);
        } else {
            em.merge(aluguel);
        }
    }

    public Aluguel porId(Integer id) {
        return dao.buscaPorId(Aluguel.class, id);
    }

    public List<Aluguel> listarAlugueisPorCliente(String clienteNome) {
        TypedQuery<Aluguel> query = em.createQuery(
                "SELECT a FROM Aluguel a JOIN a.locacao l JOIN l.cliente c WHERE c.nome = :clienteNome", Aluguel.class);
        query.setParameter("clienteNome", clienteNome);
        List<Aluguel> result = query.getResultList();
        return result;
    }

    public void listarImoveisPorLimitePreco(BigDecimal limitePreco) {
        TypedQuery<Imovel> query = em.createQuery(
                "SELECT DISTINCT i FROM Imovel i WHERE i.valorAluguelSugerido <= :limitePreco AND NOT EXISTS " +
                        "(SELECT l FROM Locacao l WHERE l.imovel = i AND l.ativo = true)", Imovel.class);
        query.setParameter("limitePreco", limitePreco);
        List<Imovel> result = query.getResultList();
        for (Imovel imovel : result) {
            System.out.println(imovel.toString());
        }
    }

    public void listarAlugueisPagosComAtraso() {
        TypedQuery<Aluguel> query = em.createQuery(
                "SELECT a FROM Aluguel a WHERE a.dataPagamento IS NOT NULL AND a.dataPagamento > a.dataVencimento", Aluguel.class);
        List<Aluguel> result = query.getResultList();
        for (Aluguel aluguel : result) {
            System.out.println(aluguel);
        }
    }
}
