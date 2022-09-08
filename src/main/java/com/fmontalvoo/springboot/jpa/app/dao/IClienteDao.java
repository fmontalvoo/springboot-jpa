package com.fmontalvoo.springboot.jpa.app.dao;

import java.util.List;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

public interface IClienteDao {

	public List<Cliente> findAll();

}
