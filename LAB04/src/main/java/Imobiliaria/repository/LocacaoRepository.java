package Imobiliaria.repository;

import Imobiliaria.entity.Locacao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LocacaoRepository {

    private final EntityManager em;
    private final DAOGenerico<Locacao> dao;

    public LocacaoRepository(EntityManager em) {
        this.em = em;
        this.dao = new DAOGenerico<>(em);
    }

    public Locacao salvaOuAtualiza(Locacao locacao) {
        if (isImovelDisponivel(locacao.getImovel().getId())) {
            return dao.salvaOuAtualiza(locacao);
        } else {
            System.out.println("Imóvel não está disponível para locação.");
            throw new IllegalArgumentException("Imóvel não está disponível para locação.");
        }
    }

    public Locacao porId(Integer id) {
        return dao.buscaPorId(Locacao.class, id);
    }

    public boolean isImovelDisponivel(Integer id) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(l) FROM Locacao l WHERE l.imovel.id = :id AND l.ativo = true", Long.class);
        query.setParameter("id", id);
        return query.getSingleResult() == 0;
    }

    public void listarLocacoesPorCliente(Integer clienteId) {
        TypedQuery<Locacao> query = em.createQuery(
                "SELECT l FROM Locacao l WHERE l.cliente.id = :clienteId", Locacao.class);
        query.setParameter("clienteId", clienteId);
        List<Locacao> result = query.getResultList();
        for (Locacao locacao : result) {
            System.out.println(locacao.toString());
        }
    }
}
