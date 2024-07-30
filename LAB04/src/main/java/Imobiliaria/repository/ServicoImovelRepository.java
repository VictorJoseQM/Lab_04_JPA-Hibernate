package Imobiliaria.repository;

import Imobiliaria.entity.ServicoImovel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServicoImovelRepository {

    private final EntityManager em;
    private final DAOGenerico<ServicoImovel> dao;

    public ServicoImovelRepository(EntityManager em) {
        this.em = em;
        this.dao = new DAOGenerico<>(em);
    }

    public ServicoImovel salvaOuAtualiza(ServicoImovel servicoImovel) {
        return dao.salvaOuAtualiza(servicoImovel);
    }

    public ServicoImovel porId(Integer id) {
        return dao.buscaPorId(ServicoImovel.class, id);
    }

    public List<ServicoImovel> todos() {
        TypedQuery<ServicoImovel> query = em.createQuery("SELECT s FROM ServicoImovel s", ServicoImovel.class);
        return query.getResultList();
    }

    public void listarServicosPorLocacao(Integer locacaoId) {
        TypedQuery<ServicoImovel> query = em.createQuery(
                "SELECT s FROM ServicoImovel s WHERE s.imovel.id = :locacaoId", ServicoImovel.class);
        query.setParameter("locacaoId", locacaoId);
        List<ServicoImovel> result = query.getResultList();
        for (ServicoImovel servicoImovel : result) {
            System.out.println(servicoImovel);
        }
    }
}

