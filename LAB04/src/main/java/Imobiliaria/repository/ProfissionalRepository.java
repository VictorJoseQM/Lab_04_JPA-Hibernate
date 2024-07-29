package Imobiliaria.repository;

import Imobiliaria.entity.Profissional;

import javax.persistence.EntityManager;
import java.util.List;

public class ProfissionalRepository {

    private final EntityManager em;
    private final DAOGenerico<Profissional> dao;

    public ProfissionalRepository(EntityManager em) {
        this.em = em;
        this.dao = new DAOGenerico<Profissional>(em);
    }

    public Profissional salvaOuAtualiza(Profissional profissional) {
        return dao.salvaOuAtualiza(profissional);
    }

    public Profissional porId(Integer id) {
        return dao.buscaPorId(Profissional.class, id);
    }

    public void listarProfissionais() {
        List<Profissional> profissionais = em.createQuery("from Profissional", Profissional.class).getResultList();
        for (Profissional profissional : profissionais) {
            System.out.println(profissional.toString());
        }
    }

}
