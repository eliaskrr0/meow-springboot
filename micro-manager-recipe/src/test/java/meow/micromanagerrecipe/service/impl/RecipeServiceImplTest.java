package meow.micromanagerrecipe.service.impl;

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
import java.util.List;

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

    }

    @Test
    void calculateRecipeNutrition() {
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
    void getFoodsByRecipe() {

    }

    @Test
    void updateRecipe() {
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
        Recipe recipe1 = new Recipe(1L, "Pastel de manzana", 278, 1L, 1L, null);
        Recipe recipe2 = new Recipe(2L, "Tostadas con huevo", 124, 2L, 1L, null);
        return List.of(recipe1, recipe2);
    }
}