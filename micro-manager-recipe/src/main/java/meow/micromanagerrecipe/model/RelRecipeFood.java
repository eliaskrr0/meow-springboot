package meow.micromanagerrecipe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_rel_recipe_food")
@IdClass(RelRecipeFood.class)
public class RelRecipeFood {
    @Id
    @Column(name = "id_rel_recipe_food", nullable = false)
    private Long idRelRecipeFood;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recipe", nullable = false)
    private Recipe recipe;

    @Id
    @Column(name = "id_food", nullable = false)
    private Long foodId;

    @NotNull
    @Positive
    @Column(name = "amount", nullable = false)
    private int amount;
}
