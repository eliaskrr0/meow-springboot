package meow.micromanagerrecipe.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import meow.common.dto.RelRecipeFoodDTO;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RelRecipeFoodId implements Serializable {
    @Schema(example = "1")
    private Long idRecipe;

    @Schema(example = "Pastel de manzana")
    private String name;

    @Schema(example = "4")
    private int amount;

    private List<RelRecipeFoodDTO> ingredients;
}
