package meow.micromanagerrecipe.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name = "tb_recipe")
public class Recipe {
    @Schema(description = ValidationMessages.COLUMN_ID_SCHEMA, accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idRecipe;

    @Schema(description = ValidationMessages.COLUMN_NAME_SCHEMA, example = "Pastel de manzana")
    @Pattern(message = ValidationMessages.COLUMN_NAME_PATTERN, regexp = "^[A-Za-z\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1 ]+$")
    @Size(message = ValidationMessages.COLUMN_NAME_SIZE, min = 2, max = 50)
    @NotBlank(message = ValidationMessages.COLUMN_NAME_NOT_BLANK)
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Schema(description = ValidationMessages.COLUMN_AMOUNT_SCHEMA, example = "100")
    @NotNull(message = ValidationMessages.COLUMN_AMOUNT_REQUIRED)
    @Positive(message = ValidationMessages.COLUMN_AMOUNT_POSITIVE)
    @Column(name = "amount", nullable = false)
    private int amount;
}
