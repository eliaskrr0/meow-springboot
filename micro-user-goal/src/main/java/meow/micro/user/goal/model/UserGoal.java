package meow.micro.user.goal.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meow.common.dto.user.goal.enums.ActivityRate;
import meow.micro.user.goal.utils.messages.ValidationMessages;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user_goal")
public class UserGoal {
    @Schema(description = ValidationMessages.COLUMN_ID_SCHEMA, accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_goal")
    private Long idUser;

    @Schema(description = ValidationMessages.COLUMN_PROTEIN_AMOUNT_SCHEMA, example = "100.00")
    @NotNull(message = ValidationMessages.COLUMN_PROTEIN_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_PROTEIN_AMOUNT_POSITIVE)
    @Digits(integer = 8, fraction = 2)
    @Column(name = "protein_target")
    private BigDecimal proteinTarget;

    @Schema(description = ValidationMessages.COLUMN_CARBS_AMOUNT_SCHEMA, example = "200.00")
    @NotNull(message = ValidationMessages.COLUMN_CARBS_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_CARBS_AMOUNT_POSITIVE)
    @Digits(integer = 8, fraction = 2)
    @Column(name = "carbs_target")
    private BigDecimal carbsTarget;

    @Schema(description = ValidationMessages.COLUMN_FAT_AMOUNT_SCHEMA, example = "50.00")
    @NotNull(message = ValidationMessages.COLUMN_FAT_AMOUNT_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_FAT_AMOUNT_POSITIVE)
    @Digits(integer = 8, fraction = 2)
    @Column(name = "fat_target")
    private BigDecimal fatTarget;

    @Schema(description = ValidationMessages.COLUMN_CALORIES_SCHEMA, example = "100.00")
    @NotNull(message = ValidationMessages.COLUMN_CALORIES_REQUIRED)
    @PositiveOrZero(message = ValidationMessages.COLUMN_CALORIES_POSITIVE)
    @Digits(integer = 8, fraction = 2)
    @Column(name = "calories_target")
    private BigDecimal caloriesTarget;

    @Schema(description = ValidationMessages.COLUMN_TYPE_TARGET_SCHEMA, example = "Manzana")
    @Size(message = ValidationMessages.COLUMN_TYPE_TARGET_SIZE, min = 1, max = 20)
    @NotBlank(message = ValidationMessages.COLUMN_TYPE_TARGET_NOT_BLANK)
    @Column(name = "type_target")
    private String typeTarget;

    @Schema(description = ValidationMessages.COLUMN_ACTIVITY_RATE_SCHEMA, example = "Sedentario")
    @Size(message = ValidationMessages.COLUMN_ACTIVITY_RATE_SIZE, min = 1, max = 10)
    @NotBlank(message = ValidationMessages.COLUMN_ACTIVITY_RATE_NOT_BLANK)
    @Column(name = "activity_rate")
    private ActivityRate activityRate;
}
