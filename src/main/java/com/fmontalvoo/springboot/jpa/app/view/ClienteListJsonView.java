package com.fmontalvoo.springboot.jpa.app.view;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

@Component("list.json")
public class ClienteListJsonView extends MappingJackson2JsonView {

	@Override
	protected Object filterModel(Map<String, Object> model) {
		model.remove("page");
		model.remove("titulo");

		Page<Cliente> lista = (Page<Cliente>) model.get("clientes");
		model.remove("clientes");

		model.put("clientes", lista.getContent());

		return super.filterModel(model);
	}

}
