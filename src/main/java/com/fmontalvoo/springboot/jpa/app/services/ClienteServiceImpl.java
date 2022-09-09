package com.fmontalvoo.springboot.jpa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fmontalvoo.springboot.jpa.app.dao.ClienteRepository;
//import com.fmontalvoo.springboot.jpa.app.dao.IClienteDao;
import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

//	@Autowired
//	private IClienteDao cdao;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

}
