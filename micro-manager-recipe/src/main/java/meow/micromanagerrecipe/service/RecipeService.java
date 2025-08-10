package meow.micromanagerrecipe.service;

import meow.common.dto.FoodDTO;
import meow.micromanagerrecipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<FoodDTO> searchFoodsByName(String name);
    Recipe saveRecipe(Recipe recipe);
    List<Recipe> getAllRecipes();
    List<Recipe> searchRecipesByName(String name);
    List<FoodDTO> getFoodsByRecipe(Long idRecipe);
    void updateRecipe(Recipe recipe);
    void deleteRecipe(Long idRecipe);
}
