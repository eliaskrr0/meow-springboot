package meow.micromanagerrecipe.model;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RelRecipeFoodId implements Serializable {
    private Long idRelRecipeFood;
    private Long foodId;
}
