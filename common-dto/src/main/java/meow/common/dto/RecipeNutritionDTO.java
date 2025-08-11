package meow.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meow.common.dto.enums.UnitMeasure;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeNutritionDTO {

    @Schema(example = "gr")
    private UnitMeasure unitMeasure;

    @Schema(example = "150")
    private int amount;

    @Schema(example = "20")
    private int proteinAmount;

    @Schema(example = "30")
    private int carbsAmount;

    @Schema(example = "10")
    private int fatAmount;

    @Schema(example = "250")
    private int calories;
}
