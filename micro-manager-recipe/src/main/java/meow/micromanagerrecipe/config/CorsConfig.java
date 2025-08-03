package meow.micromanagerrecipe.config;

import meow.micromanagerrecipe.utils.messages.ValidationMessages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(ValidationMessages.FRONTEND_URL)
                        .allowedMethods(ValidationMessages.ALLOWED_METHODS)
                        .allowedHeaders(ValidationMessages.ALLOWED_HEADERS);
            }
        };
    }
}

