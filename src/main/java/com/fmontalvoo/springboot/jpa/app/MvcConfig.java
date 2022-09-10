package com.fmontalvoo.springboot.jpa.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/**
	 * Agrega recursos estaticos al proyecto.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);

		// Mapea la ruta(/uploads/**) a un directorio externo(C:/opt/uploads/).
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/C:/opt/uploads/");

	}

}
