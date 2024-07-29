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

    public ServicoImovel buscaPorId(Integer id) {
        return dao.buscaPorId(ServicoImovel.class, id);
    }

    public void remove(ServicoImovel servicoImovel) {
        dao.remove(servicoImovel);
    }

    public List<ServicoImovel> listarServicosPorLocacao(Integer locacaoId) {
        TypedQuery<ServicoImovel> query = em.createQuery(
                "SELECT s FROM Locacao, ServicoImovel s " +
                        "WHERE Locacao.imovel.id = ServicoImovel.imovel.id ", ServicoImovel.class);
        query.setParameter("", locacaoId);
        return query.getResultList();
    }
}
