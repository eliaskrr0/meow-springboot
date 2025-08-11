package meow.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import meow.common.dto.enums.UnitMeasure;

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

    @Schema(example = "1.5")
    private int proteinAmount;

    @Schema(example = "22")
    private int carbsAmount;

    @Schema(example = "0")
    private int fatAmount;

    @Schema(example = "246")
    private int calories;
}
