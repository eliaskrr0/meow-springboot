package meow.micro.manager.recipe.config.guard;

import meow.micro.manager.recipe.config.AppProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppEnvGuardValidatorTest {
    @Mock
    private AppProperties props;
    @Mock
    private Environment mockEnv;

    @InjectMocks
    private AppEnvGuardValidator validator;

    @Test
    void run_whenEnvIsTrueAndIsLocal_thouldNotThrowException() {
        // Arrange
        props = new AppProperties();
        props.setEnvGuard(true);

        mockEnv = mock(Environment.class);
        when(mockEnv.getActiveProfiles()).thenReturn(new String[]{"local"});

        validator = new AppEnvGuardValidator(props, mockEnv);

        // Act & Assert
        assertDoesNotThrow(() -> validator.run(null));
    }

    @Test
    void run_whenEnvIsTrueIsNotLocal_thouldThrowException () {
        // Arrange
        props = new AppProperties();
        props.setEnvGuard(true);

        mockEnv = mock(Environment.class);
        when(mockEnv.getActiveProfiles()).thenReturn(new String[]{"dev", "prod"});

        validator = new AppEnvGuardValidator(props, mockEnv);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> validator.run(null));
    }

    @Test
    void run_whenEnvIsFalse_thouldThrowException () {
        // Arrange
        props = new AppProperties();
        props.setEnvGuard(false);

        mockEnv = mock(Environment.class);

        validator = new AppEnvGuardValidator(props, mockEnv);

        // Act & Assert
        assertDoesNotThrow(() -> validator.run(null));
    }
}
