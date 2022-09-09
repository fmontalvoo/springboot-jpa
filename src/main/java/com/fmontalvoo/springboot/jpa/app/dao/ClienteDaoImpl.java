package com.fmontalvoo.springboot.jpa.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

@Repository
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Cliente cliente) {
		System.out.println(cliente.toString());
		if (cliente.getId() != null && cliente.getId() > 0)
			em.merge(cliente);
		else
			em.persist(cliente);
	}

	@Override
	public Cliente findById(Long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void delete(Long id) {
		em.remove(this.findById(id));
	}

}
