package Imobiliaria.repository;

import Imobiliaria.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ClienteRepository {

    private final EntityManager em;
    private final DAOGenerico<Cliente> dao;

    public ClienteRepository(EntityManager em) {
        this.em = em;
        this.dao = new DAOGenerico<>(em);
    }

    public Cliente salvaOuAtualiza(Cliente cliente) {
        if (!isCpfUnico(cliente.getCpf(), cliente.getId())) {
            throw new IllegalArgumentException("CPF já cadastrado!");
        }
        return dao.salvaOuAtualiza(cliente);
    }

    public Cliente porId(Integer id) {
        return dao.buscaPorId(Cliente.class, id);
    }

    public boolean isCpfUnico(String cpf, Integer id) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Cliente c WHERE c.cpf = :cpf AND (:id IS NULL OR c.id != :id)", Long.class);
        query.setParameter("cpf", cpf);
        query.setParameter("id", id);
        return query.getSingleResult() == 0;
    }
}
