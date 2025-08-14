package meow.micro.manager.food.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Manager Food API",
                version = "1.0",
                description = "API de gestión alimentos"
        )
)
public class SwaggerConfig {
}
