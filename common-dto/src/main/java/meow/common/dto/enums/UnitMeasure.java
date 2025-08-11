package meow.common.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UnitMeasure {
    GR("gr"),
    ML("ml");

    private final String value;

    UnitMeasure(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UnitMeasure fromValue(String value) {
        for (UnitMeasure um : values()) {
            if (um.getValue().equalsIgnoreCase(value)) {
                return um;
            }
        }
        throw new IllegalArgumentException("Unidad inválida " + value);
    }
}
