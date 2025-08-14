package meow.common.dto.user.profile.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeTarget {
    GAIN_TWO_KILOS_OF_WEIGHT("Ganar 2kg de peso"),
    GAIN_FIVE_KILOS_OF_WEIGHT("Ganar 5kg de peso"),
    MAINTAIN_WEIGHT("Mantener peso"),
    LOSE_TWO_KILOS_OF_WEIGHT("Perder 2kg de peso"),
    LOSE_FIVE_KILOS_OF_WEIGHT("Perder 5kg de peso");

    private final String value;

    TypeTarget(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TypeTarget fromValue(String value) {
        for (TypeTarget typeTarget : values()) {
            if (typeTarget.getValue().equalsIgnoreCase(value)) {
                return typeTarget;
            }
        }
        throw new IllegalArgumentException("Tipo de objetivo incorrecto " + value);
    }
}
