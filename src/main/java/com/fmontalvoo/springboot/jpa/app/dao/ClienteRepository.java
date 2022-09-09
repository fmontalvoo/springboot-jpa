package com.fmontalvoo.springboot.jpa.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
