package com.fmontalvoo.springboot.jpa.app.dao;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

//public interface ClienteRepository extends CrudRepository<Cliente, Long> {
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

}
