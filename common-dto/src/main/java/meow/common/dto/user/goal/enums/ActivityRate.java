package meow.common.dto.user.goal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ActivityRate {
    SEDENTARY("Sedentario", 1.20),
    LIGHT("Ligera", 1.375),
    MODERATE("Moderado", 1.55),
    HEAVY("Alto", 1.725),
    VERY_HEAVY("Atleta", 1.90);

    private final String value;
    private final double factor;

    ActivityRate(String value, double factor){
        this.value = value;
        this.factor = factor;
    }

    @JsonValue
    public String getValue(){
        return value;
    }

    @JsonProperty("factor")
    public double getFactor() { return factor; }

    @JsonCreator
    public static ActivityRate fromValue(String v) {
        for (ActivityRate ar : values()) {
            if (ar.value.equalsIgnoreCase(v) || ar.name().equalsIgnoreCase(v)) return ar;
        }
        try {
            double f = Double.parseDouble(v);
            for (ActivityRate ar : values())
                if (Math.abs(ar.factor - f) < 1e-6) return ar;
        } catch (NumberFormatException ignore) { }
        throw new IllegalArgumentException("Valor de actividad incorrecto: " + v);
    }
}
