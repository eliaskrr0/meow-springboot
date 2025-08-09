package meow.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelRecipeFoodDTO {
    @Schema(example = "1")
    private Long idRecipeIngredient;

    @Schema(example = "2")
    private Long idFood;

    @Schema(example = "100")
    private int amount;
}
