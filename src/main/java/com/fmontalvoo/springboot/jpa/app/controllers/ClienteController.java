package com.fmontalvoo.springboot.jpa.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;
import com.fmontalvoo.springboot.jpa.app.services.IClienteService;

@Controller
public class ClienteController {

	@Autowired
	private IClienteService cs;

	@GetMapping("/list")
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", cs.findAll());
		return "list";
	}

	@GetMapping("/form")
	public String formulario(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("titulo", "Guardar cliente");
		model.put("cliente", cliente);
		return "form";
	}

	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Map<String, Object> model) {
		if (id != null && id > 0) {
			model.put("titulo", "Guardar cliente");
			model.put("cliente", cs.findById(id));
			return "form";
		}
		return "redirect:/list";

	}

	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Guardar cliente");
			return "form";
		}

		cs.save(cliente);
		return "redirect:/list";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable Long id) {
		if (id != null && id > 0)
			cs.delete(id);
		return "redirect:/list";
	}

}
