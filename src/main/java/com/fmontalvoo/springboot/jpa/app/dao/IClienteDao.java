package com.fmontalvoo.springboot.jpa.app.dao;

import java.util.List;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

public interface IClienteDao {

	public void save(Cliente cliente);

	public Cliente findById(Long id);

	public List<Cliente> findAll();

	public void delete(Long id);

}
