package com.fmontalvoo.springboot.jpa.app.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;
import com.fmontalvoo.springboot.jpa.app.services.IClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController {

	@Autowired
	private IClienteService cs;

	@GetMapping("/list")
	public List<Cliente> listarRest() {
		return cs.findAll();
	}

}
