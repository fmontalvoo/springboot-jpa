package com.fmontalvoo.springboot.jpa.app.dao;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

//public interface ClienteRepository extends CrudRepository<Cliente, Long> {
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWithFacturas(Long id);

}
