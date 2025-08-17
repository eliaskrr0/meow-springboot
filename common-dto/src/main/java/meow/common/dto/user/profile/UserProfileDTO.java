package meow.common.dto.user.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import meow.common.dto.user.profile.enums.TypeGender;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    @Schema(example = "Masculino")
    private TypeGender gender;

    @Schema(example = "18")
    private int age;

    @Schema(example = "170")
    private int height;
}
