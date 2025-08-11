package meow.micromanagerrecipe.service.impl;

import feign.FeignException;
import meow.common.dto.FoodDTO;
import meow.common.dto.RecipeNutritionDTO;
import meow.common.dto.enums.UnitMeasure;
import meow.micromanagerrecipe.client.FoodClient;
import meow.micromanagerrecipe.exception.ResourceNotFoundException;
import meow.micromanagerrecipe.model.Recipe;
import meow.micromanagerrecipe.model.RelRecipeFood;
import meow.micromanagerrecipe.repository.RecipeRepository;
import meow.micromanagerrecipe.service.RecipeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Transactional(readOnly = true)
    @Override
    public RecipeNutritionDTO calculateRecipeNutrition(Recipe recipe) {
        if (recipe.getIngredients() == null || recipe.getIngredients().isEmpty()) {
            return new RecipeNutritionDTO();
        }
        BigDecimal totalProtein = BigDecimal.ZERO;
        BigDecimal totalCarbs = BigDecimal.ZERO;
        BigDecimal totalFat = BigDecimal.ZERO;
        BigDecimal totalCalories = BigDecimal.ZERO;
        int totalAmount = 0;
        UnitMeasure unitMeasure = null;

        for (RelRecipeFood ingredient : recipe.getIngredients()) {
            FoodDTO food;
            try {
                food = foodClient.getFoodById(ingredient.getIdFood());
            } catch (FeignException e) {
                throw new ResourceNotFoundException(ingredient.getIdFood());
            }
            if (unitMeasure == null) {
                unitMeasure = food.getUnitMeasure();
            }
            BigDecimal factor = BigDecimal.valueOf(ingredient.getAmount())
                    .divide(BigDecimal.valueOf(food.getAmount()), 10, RoundingMode.HALF_UP);
            totalProtein = totalProtein.add(food.getProteinAmount().multiply(factor));
            totalCarbs = totalCarbs.add(food.getCarbsAmount().multiply(factor));
            totalFat = totalFat.add(food.getFatAmount().multiply(factor));
            totalCalories = totalCalories.add(food.getCalories().multiply(factor));
            totalAmount += ingredient.getAmount();
        }
        return new RecipeNutritionDTO(unitMeasure, totalAmount,
                totalProtein.setScale(2, RoundingMode.HALF_UP),
                totalCarbs.setScale(2, RoundingMode.HALF_UP),
                totalFat.setScale(2, RoundingMode.HALF_UP),
                totalCalories.setScale(2, RoundingMode.HALF_UP));
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FoodDTO> getFoodsByRecipe(Long idRecipe) {
        Recipe recipe = recipeRepository.findById(idRecipe)
                .orElseThrow(() -> new ResourceNotFoundException(idRecipe));
        if (recipe.getIngredients() == null) {
            return List.of();
        }
        return recipe.getIngredients().stream()
                .map(ingredient -> {
                    try {
                        return foodClient.getFoodById(ingredient.getIdFood());
                    } catch (FeignException e) {
                        throw new ResourceNotFoundException(ingredient.getIdFood());
                    }
                })
                .toList();
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
