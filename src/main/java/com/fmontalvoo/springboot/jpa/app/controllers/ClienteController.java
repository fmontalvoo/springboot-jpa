package com.fmontalvoo.springboot.jpa.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fmontalvoo.springboot.jpa.app.dao.IClienteDao;
import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

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

	@GetMapping("/form")
	public String formulario(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Guardar cliente");
		model.put("cliente", cliente);
		return "form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Guardar cliente");
			return "form";
		}

		cdao.save(cliente);
		return "redirect:/list";
	}

}
