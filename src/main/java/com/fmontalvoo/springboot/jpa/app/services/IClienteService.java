package com.fmontalvoo.springboot.jpa.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;
import com.fmontalvoo.springboot.jpa.app.entities.Producto;

public interface IClienteService {
	public void save(Cliente cliente);

	public Cliente findById(Long id);

	public List<Cliente> findAll();

	public Page<Cliente> findAll(Pageable pageable);

	public void delete(Long id);

	public List<Producto> findByName(String name);
}
