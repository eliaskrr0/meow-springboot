package meow.micromanagerrecipe.service.impl;

import feign.FeignException;
import meow.common.dto.FoodDTO;
import meow.micromanagerrecipe.client.FoodClient;
import meow.micromanagerrecipe.exception.ResourceNotFoundException;
import meow.micromanagerrecipe.model.Recipe;
import meow.micromanagerrecipe.repository.RecipeRepository;
import meow.micromanagerrecipe.service.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final FoodClient foodClient;
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(FoodClient foodClient, RecipeRepository recipeRepository) {
        this.foodClient = foodClient;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<FoodDTO> searchFoodsByName(String name) {
        return foodClient.searchFoodsByName(name);
    }

    @Transactional
    @Override
    public Recipe saveRecipe(Recipe recipe) {
        if (recipe.getIngredients() != null) {
            recipe.getIngredients().forEach(ingredient -> {
                try {
                    foodClient.getFoodById(ingredient.getIdFood());
                } catch (FeignException e) {
                    throw new ResourceNotFoundException(ingredient.getIdFood());
                }
                ingredient.setRecipe(recipe);
            });
        }
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional
    @Override
    public void updateRecipe(Recipe recipe) {
        if (recipeRepository.existsById(recipe.getIdRecipe())) {
            if (recipe.getIngredients() != null) {
                recipe.getIngredients().forEach(ingredient -> {
                    try {
                        foodClient.getFoodById(ingredient.getIdFood());
                    } catch (FeignException e) {
                        throw new ResourceNotFoundException(ingredient.getIdFood());
                    }
                    ingredient.setRecipe(recipe);
                });
            }
            recipeRepository.save(recipe);
        } else {
            throw new ResourceNotFoundException(recipe.getIdRecipe());
        }
    }

    @Override
    public void deleteRecipe(Long idRecipe) {
        if (recipeRepository.existsById(idRecipe)) {
            recipeRepository.deleteById(idRecipe);
        } else {
            throw new ResourceNotFoundException(idRecipe);
        }
    }
}
