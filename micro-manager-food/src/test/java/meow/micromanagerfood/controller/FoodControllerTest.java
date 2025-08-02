package meow.micromanagerfood.controller;

import meow.micromanagerfood.model.Food;
import meow.micromanagerfood.service.FoodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoodControllerTest {

    @Mock
    private FoodService foodService;

    @InjectMocks
    private FoodController foodController;

    @Test
    void getAllFoods() {
        // Arrange
        Food food1 = new Food(1L, "Pollo", "Carnicería", "gr", 100, 100, 200, 50, 370);
        Food food2 = new Food(1L, "Leche", "Hacendado", "ml", 250, 17, 0, 2, 112);
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
    void saveFood() {
        // Arrange
        Food food1 = new Food(1L, "Pollo", "Carnicería", "gr", 100, 100, 200, 50, 370);

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
        Long foodId = 1L;
        Food updatedFood = new Food(foodId, "Atún", "Calvo", "gr", 80, 22, 0, 3, 110);

        // Act
        ResponseEntity<Food> response = foodController.updateFood(foodId, updatedFood);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFood, response.getBody());
        assertEquals(foodId, updatedFood.getId());
        verify(foodService).updateFood(updatedFood);
    }

    @Test
    void deleteFood() {
        // Arrange
        Long foodId = 1L;

        // Act
        ResponseEntity<Void> response = foodController.deleteFood(foodId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(foodService).deleteFood(foodId);
    }
}