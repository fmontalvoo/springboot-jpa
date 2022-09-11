package com.fmontalvoo.springboot.jpa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fmontalvoo.springboot.jpa.app.dao.ClienteRepository;
import com.fmontalvoo.springboot.jpa.app.dao.FacturaRepository;
import com.fmontalvoo.springboot.jpa.app.dao.ProductoRepository;
//import com.fmontalvoo.springboot.jpa.app.dao.IClienteDao;
import com.fmontalvoo.springboot.jpa.app.entities.Cliente;
import com.fmontalvoo.springboot.jpa.app.entities.Factura;
import com.fmontalvoo.springboot.jpa.app.entities.Producto;

@Service
public class ClienteServiceImpl implements IClienteService {

//	@Autowired
//	private IClienteDao cdao;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private FacturaRepository facturaRepository;

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente fetchByIdWithFacturas(Long id) {
		return clienteRepository.fetchByIdWithFacturas(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProductosByName(String name) {
//		return productoRepository.buscarPorNombre(name);
		return productoRepository.findByNombreLikeIgnoreCase("%" + name + "%");
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findProductoById(Long id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaRepository.save(factura);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long id) {
		return facturaRepository.findById(id).orElse(null);
	}

	@Override
	public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id) {
		return facturaRepository.fetchByIdWithClienteWithItemFacturaWithProducto(id);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaRepository.deleteById(id);
	}

}
