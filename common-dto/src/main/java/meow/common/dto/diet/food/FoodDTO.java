package meow.common.dto.diet.food;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meow.common.dto.diet.food.enums.UnitMeasure;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {

    @Schema(example = "1")
    private Long idFood;

    @Schema(example = "Manzana")
    private String name;

    @Schema(example = "Hacendado")
    private String brand;

    @Schema(example = "gr")
    private UnitMeasure unitMeasure;

    @Schema(example = "100")
    private int amount;

    @Schema(example = "1.50")
    private BigDecimal proteinAmount;

    @Schema(example = "22.00")
    private BigDecimal carbsAmount;

    @Schema(example = "0.00")
    private BigDecimal fatAmount;

    @Schema(example = "246.00")
    private BigDecimal calories;

    @Schema(example = "2.50")
    private BigDecimal price;
}
