package meow.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meow.common.dto.enums.UnitMeasure;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeNutritionDTO {

    @Schema(example = "gr")
    private UnitMeasure unitMeasure;

    @Schema(example = "150")
    private int amount;

    @Schema(example = "20.00")
    private BigDecimal proteinAmount;

    @Schema(example = "30.00")
    private BigDecimal carbsAmount;

    @Schema(example = "10.00")
    private BigDecimal fatAmount;

    @Schema(example = "250.00")
    private BigDecimal calories;
}
