package meow.micro.user.profile.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User Profile API",
                version = "1.0",
                description = "API de gestión de perfil de usuario"
        )
)
public class SwaggerConfig {
}
