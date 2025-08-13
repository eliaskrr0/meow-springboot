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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {
    @Mock
    private FoodClient foodClient;
    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    void searchFoodsByName() {
        // Arrange
        String name = "pastel";
        List<FoodDTO> selectedFoods = getFoodDTOS();
        when(foodClient.searchFoodsByName(name)).thenReturn(selectedFoods);

        // Act
        List<FoodDTO> response = recipeService.searchFoodsByName(name);

        // Assert
        assertEquals(selectedFoods, response);

    }

    @Test
    void saveRecipe_shouldSaveRecipe_whenIngredientsAreValid() {
        // Arrange
        Recipe recipe = getRecipeWithIngredients();
        FoodDTO food = getFoodDTOS().getFirst();
        when(foodClient.getFoodById(food.getIdFood())).thenReturn(food);
        when(recipeRepository.save(recipe)).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Recipe response = recipeService.saveRecipe(recipe);

        // Assert
        assertEquals(recipe, response);
        assertEquals(recipe, recipe.getIngredients().get(0).getRecipe());
        verify(foodClient).getFoodById(food.getIdFood());
        verify(recipeRepository).save(recipe);
    }

    @Test
    void saveRecipe_shouldThrowResourceNotFound_whenFoodDoesNotExist() {
        // Arrange
        Recipe recipe = getRecipeWithIngredients();
        when(foodClient.getFoodById(anyLong())).thenThrow(mock(FeignException.class));

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> recipeService.saveRecipe(recipe));
        verify(recipeRepository, never()).save(any());
    }

    @Test
    void calculateRecipeNutrition_shouldReturnEmptyNutrition_whenRecipeHasNoIngredients() {
        // Arrange
        Recipe recipe = new Recipe(1L, "Pastel", 1L, null);

        // Act
        RecipeNutritionDTO response = recipeService.calculateRecipeNutrition(recipe);

        // Assert
        assertEquals(new RecipeNutritionDTO(), response);
    }


    @Test
    void calculateRecipeNutrition_shouldReturnNutrition_whenIngredientsAreValid() {
        // Arrange
        Recipe recipe = getRecipeWithIngredients();
        FoodDTO food = getFoodDTOS().getFirst(); // amount 100 so factor 1
        when(foodClient.getFoodById(food.getIdFood())).thenReturn(food);

        // Act
        RecipeNutritionDTO response = recipeService.calculateRecipeNutrition(recipe);

        // Assert
        RecipeNutritionDTO expected = new RecipeNutritionDTO(UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"),
                new BigDecimal("50.00"), new BigDecimal("370.00"));
        assertEquals(expected, response);
    }

    @Test
    void getFoodsByRecipe_shouldReturnFoods_whenRecipeExists() {
        // Arrange
        Recipe recipe = getRecipeWithIngredients();
        Long idRecipe = recipe.getIdRecipe();
        FoodDTO food = getFoodDTOS().getFirst();
        when(recipeRepository.findById(idRecipe)).thenReturn(Optional.of(recipe));
        when(foodClient.getFoodById(food.getIdFood())).thenReturn(food);

        // Act
        List<FoodDTO> response = recipeService.getFoodsByRecipe(idRecipe);

        // Assert
        assertEquals(List.of(food), response);
        verify(recipeRepository).findById(idRecipe);
        verify(foodClient).getFoodById(food.getIdFood());
    }

    @Test
    void getFoodsByRecipe_shouldReturnEmptyList_whenRecipeHasNoIngredients() {
        // Arrange
        Recipe recipe = new Recipe(1L, "Pastel", 1L, null);
        when(recipeRepository.findById(recipe.getIdRecipe())).thenReturn(Optional.of(recipe));

        // Act
        List<FoodDTO> response = recipeService.getFoodsByRecipe(recipe.getIdRecipe());

        // Assert
        assertTrue(response.isEmpty());
        verify(foodClient, never()).getFoodById(anyLong());
    }

    @Test
    void getFoodsByRecipe_shouldThrowResourceNotFound_whenRecipeDoesNotExist() {
        // Arrange
        Long idRecipe = 1L;
        when(recipeRepository.findById(idRecipe)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> recipeService.getFoodsByRecipe(idRecipe));
        verify(recipeRepository).findById(idRecipe);
        verify(foodClient, never()).getFoodById(anyLong());
    }

    @Test
    void getAllRecipes() {
        // Arrange
        List<Recipe> recipes = getRecipes();
        when(recipeRepository.findAll()).thenReturn(recipes);

        // Act
        List<Recipe> response = recipeService.getAllRecipes();

        // Assert
        assertEquals(recipes, response);
    }

    @Test
    void searchRecipesByName() {
        // Arrange
        String name = "pastel";
        List<Recipe> selectedRecipes = getRecipes();
        when(recipeRepository.findByNameContainingIgnoreCase(name)).thenReturn(selectedRecipes);

        // Act
        List<Recipe> response = recipeService.searchRecipesByName(name);

        // Assert
        assertEquals(selectedRecipes, response);
    }

    @Test
    void updateRecipe_shouldUpdateRecipe_whenRecipeExists() {
        // Arrange
        Recipe recipe = getRecipeWithIngredients();
        FoodDTO food = getFoodDTOS().get(0);
        when(recipeRepository.existsById(recipe.getIdRecipe())).thenReturn(true);
        when(foodClient.getFoodById(food.getIdFood())).thenReturn(food);

        // Act
        recipeService.updateRecipe(recipe);

        // Assert
        verify(recipeRepository).existsById(recipe.getIdRecipe());
        verify(foodClient).getFoodById(food.getIdFood());
        verify(recipeRepository).save(recipe);
    }

    @Test
    void updateRecipe_shouldThrowResourceNotFound_whenRecipeDoesNotExist() {
        // Arrange
        Recipe recipe = getRecipeWithIngredients();
        when(recipeRepository.existsById(recipe.getIdRecipe())).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> recipeService.updateRecipe(recipe));
        verify(recipeRepository).existsById(recipe.getIdRecipe());
        verify(recipeRepository, never()).save(any());
    }

    @Test
    void deleteRecipe_shouldDeleteRecipe_whenIdRecipeExists() {
        // Arrange
        Long idRecipe = 1L;
        when(recipeRepository.existsById(idRecipe)).thenReturn(true);

        // Act
        recipeService.deleteRecipe(idRecipe);

        // Assert
        verify(recipeRepository).existsById(idRecipe);
        verify(recipeRepository).deleteById(idRecipe);
    }

    @Test
    void deleteRecipe_shouldDeleteRecipe_whenIdRecipeDoesNotExists() {
        // Arrange
        Long idRecipe = 1L;
        when(recipeRepository.existsById(idRecipe)).thenReturn(false);


        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> recipeService.deleteRecipe(idRecipe));
        verify(recipeRepository).existsById(idRecipe);
        verify(recipeRepository, never()).deleteById(idRecipe);
    }

    // Se puede ir ampliando la lista
    private static List<FoodDTO> getFoodDTOS() {
        FoodDTO foodDTO1 = new FoodDTO(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("3.20"));
        FoodDTO foodDTO2 = new FoodDTO(2L, "Conejo", "Carnicería", UnitMeasure.GR, 43,
                new BigDecimal("53.40"), new BigDecimal("40.00"), new BigDecimal("0.4"), new BigDecimal("140.24"), new BigDecimal("5.23"));
        return List.of(foodDTO1, foodDTO2);
    }

    // Se puede ir ampliando la lista
    private static List<RecipeNutritionDTO> getRecipeNutritionDTOs() {
        RecipeNutritionDTO nutritionDTO = new RecipeNutritionDTO(UnitMeasure.GR, 150, new BigDecimal("20.00"), new BigDecimal("30.0"), new BigDecimal("10.00"), new BigDecimal("250.00"));
        return List.of(nutritionDTO);
    }

    // Se puede ir ampliando la lista
    private static List<Recipe> getRecipes() {
        Recipe recipe1 = new Recipe(1L, "Pastel de manzana", 1L, new ArrayList<>());
        Recipe recipe2 = new Recipe(2L, "Tostadas con huevo", 2L, new ArrayList<>());
        return List.of(recipe1, recipe2);
    }

    // Se puede ir ampliando la lista
    private static Recipe getRecipeWithIngredients() {
        Recipe recipe = new Recipe(1L, "Pastel", 1L, new ArrayList<>());
        RelRecipeFood ingredient = new RelRecipeFood(null, recipe, 1L, 100);
        recipe.getIngredients().add(ingredient);
        return recipe;
    }
}