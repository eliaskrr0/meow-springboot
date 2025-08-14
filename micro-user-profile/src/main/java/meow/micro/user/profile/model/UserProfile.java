package meow.micro.user.profile.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meow.common.dto.user.profile.enums.TypeGender;
import meow.common.dto.user.profile.enums.TypeTarget;
import meow.micro.user.profile.utils.messages.ValidationMessages;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_user_profile")
public class UserProfile {
    @Schema(description = ValidationMessages.COLUMN_ID_SCHEMA, accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_profile")
    private Long idUserProfile;

    @Schema(description = ValidationMessages.COLUMN_NAME_SCHEMA, example = "Aziz Bouffal")
    @Pattern(message = ValidationMessages.COLUMN_NAME_PATTERN, regexp = "^[A-Za-z\u00C1\u00C9\u00CD\u00D3\u00DA\u00E1\u00E9\u00ED\u00F3\u00FA\u00D1\u00F1 ]+$")
    @Size(message = ValidationMessages.COLUMN_NAME_SIZE, min = 2, max = 30)
    @NotBlank(message = ValidationMessages.COLUMN_NAME_NOT_BLANK)
    @Column(name = "name", nullable = false)
    private String name;

    @Schema(description = ValidationMessages.COLUMN_GENDER_SCHEMA, example = "Masculino")
    @NotNull(message = ValidationMessages.COLUMN_GENDER_REQUIRED)
    @Column(name = "gender", nullable = false)
    private TypeGender gender;

    @Schema(description = ValidationMessages.COLUMN_AGE_SCHEMA, example = "35")
    @Range(message = ValidationMessages.COLUMN_AGE_RANGE, min = 1, max = 3)
    @NotNull(message = ValidationMessages.COLUMN_AGE_REQUIRED)
    @Column(name = "age", nullable = false)
    private int age;

    @Schema(description = ValidationMessages.COLUMN_HEIGHT_SCHEMA, example = "180")
    @Range(message = ValidationMessages.COLUMN_HEIGHT_RANGE, min = 2, max = 3)
    @NotNull(message = ValidationMessages.COLUMN_HEIGHT_REQUIRED)
    @Column(name = "height", nullable = false)
    private int height;

    @Schema(description = ValidationMessages.COLUMN_TYPE_TARGET_SCHEMA, example = "Perder 2kg de peso")
    @NotNull(message = ValidationMessages.COLUMN_TYPE_TARGET_REQUIRED)
    @Column(name = "type_target", nullable = false)
    private TypeTarget typeTarget;
}
