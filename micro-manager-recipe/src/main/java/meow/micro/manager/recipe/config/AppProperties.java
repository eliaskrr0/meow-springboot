package meow.micro.manager.recipe.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private boolean envGuard;

    public boolean isEnvGuard() {
        return envGuard;
    }
    public void setEnvGuard(boolean envGuard) {
        this.envGuard = envGuard;
    }
}
