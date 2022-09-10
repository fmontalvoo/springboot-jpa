package com.fmontalvoo.springboot.jpa.app;

import java.nio.file.Paths;

//import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * Agrega recursos estaticos al proyecto.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		
		// Crea la ruta a la carpeta uploads.
		String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

		// Mapea la ruta(/uploads/**) a un directorio externo(C:/opt/uploads/).
		registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);

	}

}
