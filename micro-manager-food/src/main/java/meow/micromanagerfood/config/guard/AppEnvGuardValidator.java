package meow.micromanagerfood.config.guard;

import meow.micromanagerfood.config.AppProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * Validamos para que solo se pueda acceder desde el perfil de LOCAL para arrancar el micro
 */
public class AppEnvGuardValidator implements ApplicationRunner {
    private final AppProperties appProperties;
    private final org.springframework.core.env.Environment environment;

    public AppEnvGuardValidator(AppProperties appProperties, Environment environment) {
        this.appProperties = appProperties;
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (appProperties.isEnvGuard()) {
            String[] profiles = environment.getActiveProfiles();
            if (profiles.length == 0 || !Arrays.asList(profiles).contains("local")) {
                throw new IllegalArgumentException("El entorno debe ser local para ejecutar este servicio");
            }
        }
    }
}
