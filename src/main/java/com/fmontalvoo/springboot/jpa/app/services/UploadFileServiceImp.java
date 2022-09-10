package com.fmontalvoo.springboot.jpa.app.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImp implements IUploadFileService {

	private static final String UPLOAD_FOLDER = "uploads";
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathImg = this.getPath(filename);
		Resource resource = null;
		resource = new UrlResource(pathImg.toUri());
		if (!resource.exists() || !resource.isReadable())
			throw new RuntimeException("No se puede leer el archivo");
		return resource;
	}

	@Override
	public String copy(MultipartFile file) throws IOException {
		String[] original = file.getOriginalFilename().split("\\.");
		String extension = original[original.length - 1];
		String nombreImagen = UUID.randomUUID().toString().concat(".").concat(extension);
		Path uploadsPath = this.getPath(nombreImagen);

		logger.info("Foto: " + nombreImagen);

		Files.copy(file.getInputStream(), uploadsPath);

		return nombreImagen;
	}

	@Override
	public boolean delete(String filename) {
		Path pathImg = this.getPath(filename);
		File img = pathImg.toFile();
		return img.exists() && img.canRead() && img.delete();
	}

	public Path getPath(String filename) {
		return Paths.get(UPLOAD_FOLDER).resolve(filename).toAbsolutePath();
	}

}
