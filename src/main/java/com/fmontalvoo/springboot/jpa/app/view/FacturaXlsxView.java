package com.fmontalvoo.springboot.jpa.app.view;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.fmontalvoo.springboot.jpa.app.entities.Factura;
import com.fmontalvoo.springboot.jpa.app.entities.ItemFactura;

@Component("factura/view.xlsx")
public class FacturaXlsxView extends AbstractXlsxView {

	@Autowired
	private LocaleResolver localeResolver;

	@Autowired
	private MessageSource messageSource;

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Factura.xlsx\"");

		Factura factura = (Factura) model.get("factura");

		Locale locale = localeResolver.resolveLocale(request);

		MessageSourceAccessor mesagges = getMessageSourceAccessor();

		Sheet sheet = workbook.createSheet(factura.getCliente().getNombreCompleto());
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue("Datos del cliente");

		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellValue(messageSource.getMessage("text.cliente.nombre", null, locale)
				.concat(": ".concat(factura.getCliente().getNombreCompleto())));

		row = sheet.createRow(2);
		cell = row.createCell(0);
		cell.setCellValue(
				mesagges.getMessage("text.cliente.email").concat(": ".concat(factura.getCliente().getEmail())));

		sheet.createRow(4).createCell(0).setCellValue("Datos de la factura");
		sheet.createRow(5).createCell(0).setCellValue("Codigo: ".concat(factura.getId().toString()));
		sheet.createRow(6).createCell(0).setCellValue("Fecha: ".concat(factura.getCreatedAt().toString()));
		sheet.createRow(7).createCell(0).setCellValue("Descripcion: ".concat(factura.getDescripcion()));

		CellStyle theader = workbook.createCellStyle();
		theader.setBorderTop(BorderStyle.MEDIUM);
		theader.setBorderRight(BorderStyle.MEDIUM);
		theader.setBorderBottom(BorderStyle.MEDIUM);
		theader.setBorderLeft(BorderStyle.MEDIUM);
		theader.setFillForegroundColor(IndexedColors.BLUE_GREY.index);
		theader.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		CellStyle tbody = workbook.createCellStyle();
		tbody.setBorderTop(BorderStyle.THIN);
		tbody.setBorderRight(BorderStyle.THIN);
		tbody.setBorderBottom(BorderStyle.THIN);
		tbody.setBorderLeft(BorderStyle.THIN);

		Row header = sheet.createRow(9);
		header.createCell(0).setCellValue("Producto");
		header.createCell(1).setCellValue("Precio");
		header.createCell(2).setCellValue("Cantidad");
		header.createCell(3).setCellValue("Subtotal");

		header.getCell(0).setCellStyle(theader);
		header.getCell(1).setCellStyle(theader);
		header.getCell(2).setCellStyle(theader);
		header.getCell(3).setCellStyle(theader);

		int numRow = 10;
		for (ItemFactura item : factura.getItems()) {
			Row fila = sheet.createRow(numRow++);
			fila.createCell(0).setCellValue(item.getProducto().getNombre());
			fila.getCell(0).setCellStyle(tbody);
			fila.createCell(1).setCellValue(item.getProducto().getPrecio());
			fila.getCell(1).setCellStyle(tbody);
			fila.createCell(2).setCellValue(item.getCantidad());
			fila.getCell(2).setCellStyle(tbody);
			fila.createCell(3).setCellValue(item.calcularSubTotal());
			fila.getCell(3).setCellStyle(tbody);
		}

		Row filaTotal = sheet.createRow(numRow);
		filaTotal.createCell(2).setCellValue("Total");
		filaTotal.createCell(3).setCellValue(factura.getTotal());
	}

}
