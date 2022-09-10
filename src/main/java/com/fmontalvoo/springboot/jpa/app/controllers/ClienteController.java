package com.fmontalvoo.springboot.jpa.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;
import com.fmontalvoo.springboot.jpa.app.services.IClienteService;
import com.fmontalvoo.springboot.jpa.app.util.PageRender;

@Controller
public class ClienteController {

	@Autowired
	private IClienteService cs;

	@GetMapping("/list")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Cliente> clientes = cs.findAll(pageRequest);
		PageRender<Cliente> pageRender = new PageRender<>("/list", clientes);

		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);

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
	public String guardar(@Valid Cliente cliente, BindingResult result, RedirectAttributes flash, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Guardar cliente");
			return "form";
		}

		cs.save(cliente);
		flash.addFlashAttribute("success", "Cliente guardado con exito");
		return "redirect:/list";
	}

	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
		if (id != null && id > 0) {
			cs.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito");
		}
		return "redirect:/list";
	}

}
