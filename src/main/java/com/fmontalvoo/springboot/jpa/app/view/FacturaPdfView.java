package com.fmontalvoo.springboot.jpa.app.view;

import java.awt.Color;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.util.MessageSupplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.fmontalvoo.springboot.jpa.app.entities.Factura;
import com.fmontalvoo.springboot.jpa.app.entities.ItemFactura;
import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component("factura/view")
public class FacturaPdfView extends AbstractPdfView {

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private MessageSource messageSource;

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Factura factura = (Factura) model.get("factura");
		Locale locale = localeResolver.resolveLocale(request);

		MessageSourceAccessor mesagges = getMessageSourceAccessor();

		PdfPCell cell = null;

		PdfPTable encabezadoCliente = new PdfPTable(1);
		encabezadoCliente.setSpacingAfter(20);

		cell = new PdfPCell(new Phrase("Datos del cliente"));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBackgroundColor(new Color(184, 218, 255));
		cell.setPadding(8f);
		encabezadoCliente.addCell(cell);

		encabezadoCliente.addCell(messageSource.getMessage("text.cliente.nombre", null, locale)
				.concat(": ".concat(factura.getCliente().getNombreCompleto())));
		encabezadoCliente.addCell(
				mesagges.getMessage("text.cliente.email").concat(": ".concat(factura.getCliente().getEmail())));

		PdfPTable encabezadoFactura = new PdfPTable(1);
		encabezadoFactura.setSpacingAfter(20);

		cell = new PdfPCell(new Phrase("Datos de la factura"));
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setBackgroundColor(new Color(195, 230, 203));
		cell.setPadding(8f);
		encabezadoFactura.addCell(cell);

		encabezadoFactura.addCell("Codigo: ".concat(factura.getId().toString()));
		encabezadoFactura.addCell("Fecha: ".concat(factura.getCreatedAt().toString()));
		encabezadoFactura.addCell("Descripcion: ".concat(factura.getDescripcion()));

		PdfPTable detalleFactura = new PdfPTable(4);
//		detalleFactura.setSpacingAfter(20);
		detalleFactura.setWidths(new float[] { 3.5f, 1, 1, 1 });
		detalleFactura.addCell("Producto");
		detalleFactura.addCell("Precio");
		detalleFactura.addCell("Cantidad");
		detalleFactura.addCell("Subtotal");

		for (ItemFactura item : factura.getItems()) {
			detalleFactura.addCell(item.getProducto().getNombre());
			detalleFactura.addCell(item.getProducto().getPrecio().toString());

			cell = new PdfPCell(new Phrase(item.getCantidad().toString()));
			cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
			detalleFactura.addCell(cell);

			detalleFactura.addCell(item.calcularSubTotal().toString());
		}

		cell = new PdfPCell(new Phrase("Total: "));
		cell.setColspan(3);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		detalleFactura.addCell(cell);
		detalleFactura.addCell(factura.getTotal().toString());

		document.addTitle("Factura");
		document.add(encabezadoCliente);
		document.add(encabezadoFactura);
		document.add(detalleFactura);

	}

}
