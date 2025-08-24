package meow.common.dto.user.goal.enums.converter;

import meow.common.dto.user.goal.enums.ActivityRate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityRateConverterTest {

    private final ActivityRateConverter converter = new ActivityRateConverter();

    @Test
    void deberiaConvertirAColumnaDeBaseDeDatos() {
        assertEquals("Sedentario", converter.convertToDatabaseColumn(ActivityRate.SEDENTARY));
        assertEquals("Atleta", converter.convertToDatabaseColumn(ActivityRate.VERY_HEAVY));
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void deberiaConvertirAEntidad() {
        assertEquals(ActivityRate.SEDENTARY, converter.convertToEntityAttribute("Sedentario"));
        assertEquals(ActivityRate.VERY_HEAVY, converter.convertToEntityAttribute("Atleta"));
        assertEquals(ActivityRate.MODERATE, converter.convertToEntityAttribute("1.55"));
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    void deberiaLanzarExcepcionParaValorInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                converter.convertToEntityAttribute("desconocido")
        );
        assertTrue(exception.getMessage().contains("Valor de actividad incorrecto"));
    }
}