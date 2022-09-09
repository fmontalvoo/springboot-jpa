package com.fmontalvoo.springboot.jpa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fmontalvoo.springboot.jpa.app.dao.IClienteDao;
import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao cdao;

	@Override
	@Transactional
	public void save(Cliente cliente) {
		cdao.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return cdao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return cdao.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cdao.delete(id);
	}

}
