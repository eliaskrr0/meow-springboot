package meow.micromanagerfood.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meow.micromanagerfood.utils.messages.ValidationMessages;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Pattern(message = ValidationMessages.COLUMN_NAME_PATTERN, regexp = "^[A-Za-z\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1 ]+$")
    @Size(message = ValidationMessages.COLUMN_NAME_SIZE, min = 2, max = 50)
    @NotBlank(message = ValidationMessages.COLUMN_NAME_NOT_BLANK)
    @Column(name = "name")
    private String name;

    @Pattern(message = ValidationMessages.COLUMN_BRAND_PATTERN, regexp = "^[A-Za-z\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1 ]+$")
    @Size(message = ValidationMessages.COLUMN_BRAND_SIZE, min = 2, max = 50)
    @NotBlank(message = ValidationMessages.COLUMN_BRAND_NOT_BLANK)
    @Column(name = "brand")
    private String brand;

    @NotNull(message = ValidationMessages.COLUMN_UNIT_MEASURE_REQUIRED)
    @Column(name = "unit_measure", nullable = false)
    private int unitMeasure;

    @NotNull(message = ValidationMessages.COLUMN_AMOUNT_REQUIRED)
    @Positive(message = ValidationMessages.COLUMN_AMOUNT_POSITIVE)
    private int amount;

    @NotNull(message = ValidationMessages.COLUMN_PROTEIN_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_PROTEIN_AMOUNT_POSITIVE)
    private int proteinAmount;

    @NotNull(message = ValidationMessages.COLUMN_CARBS_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_CARBS_AMOUNT_POSITIVE)
    private int carbsAmount;

    @NotNull(message = ValidationMessages.COLUMN_FAT_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_FAT_AMOUNT_POSITIVE)
    private int fatAmount;

    @NotNull(message = ValidationMessages.COLUMN_CALORIES_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_CALORIES_POSITIVE)
    private int calories;

}
