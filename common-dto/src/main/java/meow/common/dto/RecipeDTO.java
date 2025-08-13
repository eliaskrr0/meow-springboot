package meow.common.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class RecipeDTO {
    @Schema(example = "1")
    private Long idRecipe;

    @Schema(example = "Pastel de manzana")
    private String name;

    @Schema(example = "1")
    private Long version;

    private List<RelRecipeFoodDTO> ingredients;
}
