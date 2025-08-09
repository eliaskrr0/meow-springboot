package meow.micromanagerfood.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meow.micromanagerfood.model.enums.UnitMeasure;
import meow.micromanagerfood.model.enums.converter.UnitMeasureConverter;
import meow.micromanagerfood.utils.messages.ValidationMessages;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_food")
public class Food {
    @Schema(description = ValidationMessages.COLUMN_ID_SCHEMA, accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_food", nullable = false)
    private Long idFood;

    @Schema(description = ValidationMessages.COLUMN_NAME_SCHEMA, example = "Manzana")
    @Pattern(message = ValidationMessages.COLUMN_NAME_PATTERN, regexp = "^[A-Za-z\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1 ]+$")
    @Size(message = ValidationMessages.COLUMN_NAME_SIZE, min = 2, max = 50)
    @NotBlank(message = ValidationMessages.COLUMN_NAME_NOT_BLANK)
    @Column(name = "name")
    private String name;

    @Schema(description = ValidationMessages.COLUMN_BRAND_SCHEMA, example = "Hacendado")
    @Pattern(message = ValidationMessages.COLUMN_BRAND_PATTERN, regexp = "^[A-Za-z\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1 ]+$")
    @Size(message = ValidationMessages.COLUMN_BRAND_SIZE, min = 2, max = 50)
    @NotBlank(message = ValidationMessages.COLUMN_BRAND_NOT_BLANK)
    @Column(name = "brand")
    private String brand;

    @Schema(description = ValidationMessages.COLUMN_UNIT_MEASURE_SCHEMA, example = "gr")
    @NotNull(message = ValidationMessages.COLUMN_UNIT_MEASURE_REQUIRED)
    @Column(name = "unit_measure", nullable = false)
    @Convert(converter = UnitMeasureConverter.class)
    private UnitMeasure unitMeasure;

    @Schema(description = ValidationMessages.COLUMN_AMOUNT_SCHEMA, example = "100")
    @NotNull(message = ValidationMessages.COLUMN_AMOUNT_REQUIRED)
    @Positive(message = ValidationMessages.COLUMN_AMOUNT_POSITIVE)
    private int amount;

    @Schema(description = ValidationMessages.COLUMN_PROTEIN_AMOUNT_SCHEMA, example = "100")
    @NotNull(message = ValidationMessages.COLUMN_PROTEIN_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_PROTEIN_AMOUNT_POSITIVE)
    private int proteinAmount;

    @Schema(description = ValidationMessages.COLUMN_CARBS_AMOUNT_SCHEMA, example = "200")
    @NotNull(message = ValidationMessages.COLUMN_CARBS_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_CARBS_AMOUNT_POSITIVE)
    private int carbsAmount;

    @Schema(description = ValidationMessages.COLUMN_FAT_AMOUNT_SCHEMA, example = "50")
    @NotNull(message = ValidationMessages.COLUMN_FAT_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_FAT_AMOUNT_POSITIVE)
    private int fatAmount;

    @Schema(description = ValidationMessages.COLUMN_CALORIES_SCHEMA, example = "100")
    @NotNull(message = ValidationMessages.COLUMN_CALORIES_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_CALORIES_POSITIVE)
    private int calories;

}
