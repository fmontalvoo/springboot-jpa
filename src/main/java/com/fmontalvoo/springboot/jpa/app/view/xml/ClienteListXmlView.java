package com.fmontalvoo.springboot.jpa.app.view.xml;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.xml.MarshallingView;

import com.fmontalvoo.springboot.jpa.app.entities.Cliente;

@Component("list.xml")
public class ClienteListXmlView extends MarshallingView {

	@Autowired
	public ClienteListXmlView(Jaxb2Marshaller marshaller) {
		super(marshaller);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		model.remove("page");
		model.remove("titulo");
		Page<Cliente> lista = (Page<Cliente>) model.get("clientes");
		model.remove("clientes");

		model.put("lista", new ClienteList(lista.getContent()));

		super.renderMergedOutputModel(model, request, response);
	}

}
