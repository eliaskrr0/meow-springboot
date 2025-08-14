package meow.common.dto.diet.recipe;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelRecipeFoodDTO {
    @Schema(example = "1")
    private Long idRelRecipeFood;

    private RecipeDTO recipeDTO;

    @Schema(example = "1")
    private Long idFood;

    @Schema(example = "100")
    private int amount;
}
