package co.edu.unbosque.shopease_app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // aplica a todas las rutas
                .allowedOrigins("http://localhost:4200") // origen del frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // incluye OPTIONS
                .allowedHeaders("*")
                .allowCredentials(true); // si usas cookies/sesiones
    }
}
