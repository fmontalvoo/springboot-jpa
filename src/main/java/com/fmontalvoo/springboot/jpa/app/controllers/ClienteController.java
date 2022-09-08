package com.fmontalvoo.springboot.jpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fmontalvoo.springboot.jpa.app.dao.IClienteDao;

@Controller
public class ClienteController {

	@Autowired
	private IClienteDao cdao;

	@GetMapping("/list")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", cdao.findAll());
		return "list";
	}

}
