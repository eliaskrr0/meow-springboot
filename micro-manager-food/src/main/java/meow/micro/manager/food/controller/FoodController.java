package meow.micro.manager.food.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import meow.common.dto.diet.food.FoodDTO;
import meow.common.dto.diet.food.enums.UnitMeasure;
import meow.micro.manager.food.mapper.FoodMapper;
import meow.micro.manager.food.model.Food;
import meow.micro.manager.food.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Tag(name = "Food", description = "API de alimentos")
@RestController
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodService foodService;
    private final FoodMapper foodMapper;

    public FoodController(FoodService foodService, FoodMapper foodMapper) {
        this.foodService = foodService;
        this.foodMapper = foodMapper;
    }

    @Operation(summary = "Obtiene todos los alimentos")
    @GetMapping("/list")
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @Operation(summary = "Obtiene un alimento por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        return ResponseEntity.ok(foodService.getFoodById(id));
    }

    @Operation(summary = "Obtiene un alimento por su nombre")
    @GetMapping("/search")
    public ResponseEntity<List<FoodDTO>> searchFoodsByName(@RequestParam String name) {
        List<Food> foods = foodService.searchFoodByName(name);
        List<FoodDTO> dtoList = foodMapper.toDTOList(foods);
        return ResponseEntity.ok(dtoList);
    }

    @ApiResponse(responseCode = "201", description = "Alimento creado correctamente")
    @Operation(summary = "Guarda un alimento")
    @PostMapping("/add")
    public ResponseEntity<Food> saveFood(@Valid @RequestBody Food food) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodService.saveFood(food));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alimento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    })
    @Operation(summary = "Actualiza alimento filtrando por su ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @Valid @RequestBody Food food) {
        food.setIdFood(id);
        foodService.updateFood(food);
        return ResponseEntity.ok(food);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Alimento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    })
    @Operation(summary = "Elimina un alimento por su ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtiene las unidades de medida disponibles")
    @GetMapping("/unit-measures")
    public ResponseEntity<List<UnitMeasure>> getUnitMeasures() {
        return ResponseEntity.ok(Arrays.asList(UnitMeasure.values()));
    }
}