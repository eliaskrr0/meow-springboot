package meow.micro.user.goal.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User Goal API",
                version = "1.0",
                description = "API de gestión objetivos del usuario"
        )
)
public class SwaggerConfig {
}
