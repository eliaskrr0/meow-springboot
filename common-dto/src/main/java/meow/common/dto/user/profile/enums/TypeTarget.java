package meow.common.dto.user.profile.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeTarget {
    GAIN_WEIGHT_AGGRESSIVELY("Ganar 0,5% de peso"),
    GAIN_WEIGHT_MODERATELY("Ganar 0,35% de peso"),
    GAIN_WEIGHT_SLOWLY("Ganar 0,25% de peso"),
    MAINTAIN_WEIGHT("Mantener peso"),
    LOSE_WEIGHT_SLOWLY("Perder 0,25% de peso"),
    LOSE_WEIGHT_MODERATELY("Perder 0,5% de peso"),
    LOSE_WEIGHT_AGGRESSIVELY("Perder 0,75% de peso");

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
