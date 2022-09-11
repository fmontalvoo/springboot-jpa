package com.fmontalvoo.springboot.jpa.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.fmontalvoo.springboot.jpa.app.entities.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Long> {

}
