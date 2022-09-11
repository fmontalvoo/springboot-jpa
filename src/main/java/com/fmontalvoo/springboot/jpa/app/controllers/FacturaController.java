package com.fmontalvoo.springboot.jpa.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;
import com.fmontalvoo.springboot.jpa.app.entities.Factura;
import com.fmontalvoo.springboot.jpa.app.entities.Producto;
import com.fmontalvoo.springboot.jpa.app.services.IClienteService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private IClienteService cs;

	@GetMapping("/form/{clienteId}")
	public String crear(@PathVariable Long clienteId, Model model, RedirectAttributes flash) {

		Cliente cliente = cs.findById(clienteId);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirect:/list";
		}

		Factura factura = new Factura();
		factura.setCliente(cliente);

		model.addAttribute("titulo", "Crear factura -> ");
		model.addAttribute("factura", factura);

		return "factura/form";
	}

	@GetMapping(value = "/buscar-productos/{nombre}", produces = { "application/json" })
	public @ResponseBody List<Producto> bucarProductos(@PathVariable String nombre, Model model) {
		return cs.findByName(nombre);
	}

}
