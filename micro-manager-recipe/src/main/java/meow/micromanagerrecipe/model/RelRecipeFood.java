package meow.micromanagerrecipe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meow.micromanagerrecipe.utils.messages.ValidationMessages;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_rel_recipe_food")
public class RelRecipeFood {
    @Schema(description = ValidationMessages.COLUMN_ID_REL_RECIPE_FOOD_SCHEMA, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rel_recipe_food", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long idRelRecipeFood;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe", nullable = false)
    private Recipe recipe;

    @NotNull
    @Schema(description = ValidationMessages.COLUMN_FOOD_ID_SCHEMA, example = "1")
    @NotNull(message = ValidationMessages.COLUMN_FOOD_ID_REQUIRED)
    @Column(name = "id_food", nullable = false)
    private Long idFood;

    @Schema(description = ValidationMessages.COLUMN_AMOUNT_SCHEMA, example = "100")
    @NotNull(message = ValidationMessages.COLUMN_AMOUNT_REQUIRED)
    @Positive(message = ValidationMessages.COLUMN_AMOUNT_POSITIVE)
    @Column(name = "amount", nullable = false)
    private int amount;
}
