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
    @GetMapping
    public ResponseEntity<List<FoodDTO>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return ResponseEntity.ok(foodMapper.toDTOList(foods));
    }

    @Operation(summary = "Obtiene un alimento por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable Long id) {
        Food food = foodService.getFoodById(id);
        return ResponseEntity.ok(foodMapper.toDTO(food));
    }

    @Operation(summary = "Obtiene un alimento por su nombre")
    @GetMapping("/search")
    public ResponseEntity<List<FoodDTO>> searchFoodsByName(@RequestParam String name) {
        List<Food> foods = foodService.searchFoodByName(name);
        return ResponseEntity.ok(foodMapper.toDTOList(foods));
    }

    @ApiResponse(responseCode = "201", description = "Alimento creado correctamente")
    @Operation(summary = "Guarda un alimento")
    @PostMapping
    public ResponseEntity<FoodDTO> saveFood(@Valid @RequestBody FoodDTO foodDTO) {
        Food food = foodMapper.toEntity(foodDTO);
        Food saved = foodService.saveFood(food);
        return ResponseEntity.status(HttpStatus.CREATED).body(foodMapper.toDTO(saved));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Alimento actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    })
    @Operation(summary = "Actualiza alimento filtrando por su ID")
    @PutMapping("/{id}")
    public ResponseEntity<FoodDTO> updateFood(@PathVariable Long id, @Valid @RequestBody FoodDTO foodDTO) {
        Food food = foodMapper.toEntity(foodDTO);
        food.setIdFood(id);
        Food updated = foodService.updateFood(food);
        return ResponseEntity.ok(foodMapper.toDTO(updated));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Alimento eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Alimento no encontrado")
    })
    @Operation(summary = "Elimina un alimento por su ID")
    @DeleteMapping("/{id}")
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