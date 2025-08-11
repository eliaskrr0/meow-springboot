package meow.micromanagerrecipe.controller;

import meow.common.dto.FoodDTO;
import meow.common.dto.RecipeNutritionDTO;
import meow.common.dto.enums.UnitMeasure;
import meow.micromanagerrecipe.model.Recipe;
import meow.micromanagerrecipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {
    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController controller;

    @Test
    void getAllRecipes() {
        // Arrange
        List<Recipe> recipes = getRecipes();

        when(recipeService.getAllRecipes()).thenReturn(recipes);

        // Act
        ResponseEntity<List<Recipe>> response = controller.getAllRecipes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipes, response.getBody());
        verify(recipeService).getAllRecipes();
    }

    @Test
    void searchRecipesByName() {
        // Arrange
        String name = "pastel";
        List<Recipe> recipes = getRecipes();

        List<Recipe> selectedRecipe = recipes.stream().filter(recipe -> recipe.getName().contains(name)).toList();
        when(recipeService.searchRecipesByName(name)).thenReturn(selectedRecipe);

        // Act
        ResponseEntity<List<Recipe>> response = controller.searchRecipesByName(name);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(selectedRecipe, response.getBody());
        verify(recipeService).searchRecipesByName(name);
    }

    @Test
    void getFoodsByRecipe() {
        // Arrange
        Long idFood = 1L;
        Long idRecipe = 1L;
        List<FoodDTO> foods = getFoodDTOS();

        List<FoodDTO> selectedFood = foods.stream().filter(foodDTO -> foodDTO.getIdFood().equals(idFood)).toList();
        when(recipeService.getFoodsByRecipe(idRecipe)).thenReturn(selectedFood);

        // Act
        ResponseEntity<List<FoodDTO>> response = controller.getFoodsByRecipe(idRecipe);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(selectedFood, response.getBody());
        verify(recipeService).getFoodsByRecipe(idRecipe);
    }

    @Test
    void saveRecipe() {
        // Arrange
        Recipe recipe = getRecipes().getFirst();

        when(recipeService.saveRecipe(recipe)).thenReturn(recipe);

        // Act
        ResponseEntity<Recipe> response = controller.saveRecipe(recipe);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(recipe, response.getBody());
        verify(recipeService).saveRecipe(recipe);
    }

    @Test
    void calculateRecipe() {
        // Arrange
        Recipe selectedRecipe = getRecipes().getFirst();
        RecipeNutritionDTO recipeNutritionDTO = getRecipeNutritionDTOs().getFirst();

        when(recipeService.calculateRecipeNutrition(selectedRecipe)).thenReturn(recipeNutritionDTO);

        // Act
        ResponseEntity<RecipeNutritionDTO> response = controller.calculateRecipe(selectedRecipe);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(recipeNutritionDTO, response.getBody());
        verify(recipeService).calculateRecipeNutrition(selectedRecipe);

    }

    @Test
    void updateRecipe() {
        // Arrange
        Long idRecipe = 1L;
        Recipe updatedRecipe = getRecipes().getFirst();

        // Act
        ResponseEntity<Recipe> response = controller.updateRecipe(idRecipe, updatedRecipe);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedRecipe, response.getBody());
        verify(recipeService).updateRecipe(updatedRecipe);
    }

    @Test
    void deleteRecipe() {
        // Assert
        Long idRecipe = 1L;

        // Act
        ResponseEntity<Void> response = controller.deleteRecipe(idRecipe);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(recipeService).deleteRecipe(idRecipe);
    }

    private static List<FoodDTO> getFoodDTOS() {
        FoodDTO foodDTO1 = new FoodDTO(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("3.20"));
        FoodDTO foodDTO2 = new FoodDTO(2L, "Conejo", "Carnicería", UnitMeasure.GR, 43,
                new BigDecimal("53.40"), new BigDecimal("40.00"), new BigDecimal("0.4"), new BigDecimal("140.24"), new BigDecimal("5.23"));
        return List.of(foodDTO1, foodDTO2);
    }

    // Se puede ir amplicando la lista
    private static List<RecipeNutritionDTO> getRecipeNutritionDTOs() {
        RecipeNutritionDTO nutritionDTO = new RecipeNutritionDTO(UnitMeasure.GR, 150, new BigDecimal("20.00"), new BigDecimal("30.0"), new BigDecimal("10.00"), new BigDecimal("250.00"));
        return List.of(nutritionDTO);
    }

    private static List<Recipe> getRecipes() {
        Recipe recipe1 = new Recipe(1L, "Pastel de manzana", 278, 1L, 1L, null);
        Recipe recipe2 = new Recipe(2L, "Tostadas con huevo", 124, 2L, 1L, null);
        return List.of(recipe1, recipe2);
    }
}