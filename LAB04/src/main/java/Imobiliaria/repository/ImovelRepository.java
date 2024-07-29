package Imobiliaria.repository;

import Imobiliaria.entity.Imovel;

import javax.persistence.EntityManager;
import java.util.List;

public class ImovelRepository {

    private final EntityManager em;
    private final DAOGenerico<Imovel> dao;

    public ImovelRepository(EntityManager em) {
        this.em = em;
        this.dao = new DAOGenerico<Imovel>(em);
    }

    public Imovel salvaOuAtualiza(Imovel imovel) {
        return dao.salvaOuAtualiza(imovel);
    }

    public Imovel porId(Integer id) {
        return dao.buscaPorId(Imovel.class, id);
    }

    public void listarImoveis() {
        List<Imovel> imoveis = em.createQuery("from Imovel", Imovel.class).getResultList();
        for (Imovel imovel : imoveis) {
            System.out.println(imovel.toString());
        }
    }
}
