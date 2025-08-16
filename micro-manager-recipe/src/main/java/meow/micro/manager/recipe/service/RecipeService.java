package meow.micro.manager.recipe.service;

import meow.common.dto.diet.food.FoodDTO;
import meow.common.dto.diet.recipe.RecipeNutritionDTO;
import meow.micro.manager.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<FoodDTO> searchFoodsByName(String name);
    Recipe saveRecipe(Recipe recipe);
    RecipeNutritionDTO calculateRecipeNutrition(Recipe recipe);
    List<Recipe> getAllRecipes();
    List<Recipe> searchRecipesByName(String name);
    List<FoodDTO> getFoodsByRecipe(Long idRecipe);
    Recipe updateRecipe(Recipe recipe);
    void deleteRecipe(Long idRecipe);
}
