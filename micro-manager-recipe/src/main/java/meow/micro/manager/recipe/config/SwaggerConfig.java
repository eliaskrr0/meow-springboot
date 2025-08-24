package meow.micro.manager.recipe.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Manager Recipe API",
                version = "1.0",
                description = "API de gestión recetas"
        )
)
public class SwaggerConfig {
}
