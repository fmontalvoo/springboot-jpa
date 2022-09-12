package com.fmontalvoo.springboot.jpa.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.fmontalvoo.springboot.jpa.app.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	public Usuario findByUsername(String username); 
	
}
