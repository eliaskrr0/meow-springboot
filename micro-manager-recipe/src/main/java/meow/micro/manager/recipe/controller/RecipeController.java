package meow.micro.manager.recipe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import meow.common.dto.diet.food.FoodDTO;
import meow.common.dto.diet.recipe.RecipeNutritionDTO;
import meow.micro.manager.recipe.model.Recipe;
import meow.micro.manager.recipe.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Recipes", description = "API de recetas")
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(summary = "Obtiene todas las recetas")
    @GetMapping("/list")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @Operation(summary = "Obtiene una receta por su nombre")
    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipesByName(String name) {
        return ResponseEntity.ok(recipeService.searchRecipesByName(name));
    }

    @Operation(summary = "Obtiene los alimentos de una receta")
    @GetMapping("/{id}/foods")
    public ResponseEntity<List<FoodDTO>> getFoodsByRecipe(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getFoodsByRecipe(id));
    }

    @ApiResponse(responseCode = "201", description = "Receta creada correctamente")
    @Operation(summary = "Guarda una receta")
    @PostMapping("/add")
    public ResponseEntity<Recipe> saveRecipe(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.saveRecipe(recipe));
    }

    @Operation(summary = "Calcula los datos nutricionales de una receta")
    @PostMapping("/calculate")
    public ResponseEntity<RecipeNutritionDTO> calculateRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.calculateRecipeNutrition(recipe));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alimento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    })
    @Operation(summary = "Actualiza alimento filtrando por su ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @Valid @RequestBody Recipe recipe) {
        recipe.setIdRecipe(id);
        recipeService.updateRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alimento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    })
    @Operation(summary = "Elimina alimento filtrando por su ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
