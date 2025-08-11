package meow.micromanagerfood.controller;

import meow.common.dto.FoodDTO;
import meow.micromanagerfood.mapper.FoodMapper;
import meow.micromanagerfood.model.Food;
import meow.common.dto.enums.UnitMeasure;
import meow.micromanagerfood.service.FoodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodControllerTest {

    @Mock
    private FoodService foodService;
    @Mock
    private FoodMapper foodMapper;

    @InjectMocks
    private FoodController foodController;

    @Test
    void getAllFoods() {
        // Arrange
        Food food1 = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("1.20"));
        Food food2 = new Food(1L, "Leche", "Hacendado", UnitMeasure.ML, 250,
                new BigDecimal("17.00"), new BigDecimal("0.00"), new BigDecimal("2.00"), new BigDecimal("112.00"), new BigDecimal("1.20"));
        List<Food> mockFoods = List.of(food1, food2);

        when(foodService.getAllFoods()).thenReturn(mockFoods);

        // Act
        ResponseEntity<List<Food>> response = foodController.getAllFoods();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockFoods, response.getBody());
        verify(foodService).getAllFoods();
    }

    @Test
    void searchFoodsByName() {
        // Arrange
        String name = "Pollo";
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("3.20"));
        FoodDTO foodDTO = new FoodDTO(1L, "Leche", "Hacendado", UnitMeasure.ML, 250,
                new BigDecimal("17.00"), new BigDecimal("0.00"), new BigDecimal("2.00"), new BigDecimal("112.00"), new BigDecimal("1.20"));
        List<Food> foods = List.of(food);
        List<FoodDTO> dtoList = List.of(foodDTO);

        when(foodService.searchFoodByName(name)).thenReturn(foods);
        when(foodMapper.toDTOList(foods)).thenReturn(dtoList);

        // Act
        ResponseEntity<List<FoodDTO>> response = foodController.searchFoodsByName(name);

        // Arrange
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dtoList, response.getBody());
        verify(foodService).searchFoodByName(name);
        verify(foodMapper).toDTOList(foods);
    }

    @Test
    void saveFood() {
        // Arrange
        Food food1 = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("1.20"));

        when(foodService.saveFood(food1)).thenReturn(food1);

        // Act
        ResponseEntity<Food> response = foodController.saveFood(food1);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(food1, response.getBody());
        verify(foodService).saveFood(food1);
    }

    @Test
    void updateFood() {
        // Arrange
        Long idFood = 1L;
        Food updatedFood = new Food(idFood, "Atún", "Calvo", UnitMeasure.GR, 80,
                new BigDecimal("22.00"), new BigDecimal("0.00"), new BigDecimal("3.00"), new BigDecimal("110.00"), new BigDecimal("1.20"));

        // Act
        ResponseEntity<Food> response = foodController.updateFood(idFood, updatedFood);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFood, response.getBody());
        assertEquals(idFood, updatedFood.getIdFood());
        verify(foodService).updateFood(updatedFood);
    }

    @Test
    void deleteFood() {
        // Arrange
        Long idFood = 1L;

        // Act
        ResponseEntity<Void> response = foodController.deleteFood(idFood);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(foodService).deleteFood(idFood);
    }
}