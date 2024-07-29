package Imobiliaria.repository;

import Imobiliaria.entity.TipoImovel;

import javax.persistence.EntityManager;
import java.util.List;

public class TipoImovelRepository {

    private final EntityManager em;
    private final DAOGenerico<TipoImovel> dao;

    public TipoImovelRepository(EntityManager em) {
        this.em = em;
        this.dao = new DAOGenerico<>(em);
    }

    public TipoImovel salvaOuAtualiza(TipoImovel tipoImovel) {
        return dao.salvaOuAtualiza(tipoImovel);
    }

    public TipoImovel porId(Integer id) {
        return dao.buscaPorId(TipoImovel.class, id);
    }

    public void listarTiposImovel() {
        List<TipoImovel> tiposImovel = em.createQuery("from TipoImovel", TipoImovel.class).getResultList();
        for (TipoImovel tipoImovel : tiposImovel) {
            System.out.println(tipoImovel.toString());
        }
    }
}
