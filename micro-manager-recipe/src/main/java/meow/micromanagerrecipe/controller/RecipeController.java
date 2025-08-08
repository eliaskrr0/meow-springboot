package meow.micromanagerrecipe.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import meow.micromanagerrecipe.model.Recipe;
import meow.micromanagerrecipe.service.RecipeService;
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

    @ApiResponse(responseCode = "201", description = "Receta creada correctamente")
    @Operation(summary = "Guarda una receta")
    @PutMapping("/add")
    public ResponseEntity<Recipe> saveRecipe(Recipe recipe) {
        return ResponseEntity.ok(recipeService.saveRecipe(recipe));
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
