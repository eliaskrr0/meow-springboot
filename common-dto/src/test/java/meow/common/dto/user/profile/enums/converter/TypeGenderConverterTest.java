package meow.common.dto.user.profile.enums.converter;

import meow.common.dto.user.profile.enums.TypeGender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeGenderConverterTest {
    private final TypeGenderConverter converter = new TypeGenderConverter();

    @Test
    void shouldConvertToDatabaseColumn() {
        assertEquals("Masculino", converter.convertToDatabaseColumn(TypeGender.MASCULINE));
        assertEquals("Femenino", converter.convertToDatabaseColumn(TypeGender.FEMININE));
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    void shouldConvertToEntityAttribute() {
        assertEquals(TypeGender.MASCULINE, converter.convertToEntityAttribute("Masculino"));
        assertEquals(TypeGender.FEMININE, converter.convertToEntityAttribute("Femenino"));
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    void shouldThrowExceptionForInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                converter.convertToEntityAttribute("invalid")
        );
        assertTrue(exception.getMessage().contains("Tipo de genero incorrecto"));
    }

}