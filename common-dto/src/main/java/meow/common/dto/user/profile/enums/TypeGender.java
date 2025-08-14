package meow.common.dto.user.profile.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeGender {
    MASCULINE ("Masculino"),
    FEMININE ("Femenino");

    private final String value;

    TypeGender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TypeGender fromValue(String value) {
        for (TypeGender typeGender : values()) {
            if (typeGender.getValue().equalsIgnoreCase(value)) {
                return typeGender;
            }
        }
        throw new IllegalArgumentException("Tipo de genero incorrecto " + value);
    }
}
