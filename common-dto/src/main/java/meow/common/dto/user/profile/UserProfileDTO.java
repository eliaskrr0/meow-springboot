package meow.common.dto.user.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import meow.common.dto.user.profile.enums.TypeGender;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    @Schema(example = "1L")
    private Long idUserProfile;

    @Schema(example = "Mohammed")
    private String name;

    @Schema(example = "Masculino")
    private TypeGender gender;

    @Schema(example = "18")
    private int age;

    @Schema(example = "180")
    private int height;

    @Schema(example = "78")
    private BigDecimal weight;
}
