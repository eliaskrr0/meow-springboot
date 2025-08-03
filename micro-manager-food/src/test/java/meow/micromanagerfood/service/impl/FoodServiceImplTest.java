package meow.micromanagerfood.service.impl;

import meow.micromanagerfood.exception.ResourceNotFoundException;
import meow.micromanagerfood.model.Food;
import meow.micromanagerfood.repository.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FoodServiceImplTest {

    @Mock
    private FoodRepository foodRepository;

    @InjectMocks
    private FoodServiceImpl foodService;

    @Test
    void saveFood() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", "gr", 100, 100, 200, 50, 370);
        when(foodRepository.save(food)).thenReturn(food);

        // Act
        Food result = foodService.saveFood(food);

        // Assert
        assertEquals(food, result);
        verify(foodRepository, times(1)).save(food);

    }

    @Test
    void getAllFoods() {
        // Arrange
        Food food1 = new Food(1L, "Pollo", "Carnicería", "gr", 100, 100, 200, 50, 370);
        Food food2 = new Food(1L, "Leche", "Hacendado", "ml", 250, 17, 0, 2, 112);
        List<Food> mockFoods = List.of(food1, food2);

        when(foodRepository.findAll()).thenReturn(mockFoods);

        // Act
        List<Food> result = foodService.getAllFoods();

        // Assert
        assertEquals(mockFoods, result);
    }

    @Test
    void updateFood_shouldUpdate_whenFoodExists() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", "gr", 100, 100, 200, 50, 370);
        when(foodRepository.existsById(food.getIdFood())).thenReturn(true);

        // Act
        foodService.updateFood(food);

        // Assert
        verify(foodRepository).existsById(food.getIdFood());
        verify(foodRepository).save(food);
    }

    @Test
    void updateFood_shouldThrowResourceNotFoundException_whenFoodDoesNotExists() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", "gr", 100, 100, 200, 50, 370);
        when(foodRepository.existsById(food.getIdFood())).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> foodService.updateFood(food));
        verify(foodRepository).existsById(food.getIdFood());
        verify(foodRepository, never()).save(food);


    }

    @Test
    void deleteFood_shouldDelete_whenFoodExists() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", "gr", 100, 100, 200, 50, 370);
        when(foodRepository.existsById(food.getIdFood())).thenReturn(true);

        // Act
        foodService.deleteFood(food.getIdFood());

        // Assert
        verify(foodRepository).existsById(food.getIdFood());
        verify(foodRepository).deleteById(food.getIdFood());

    }

    @Test
    void deleteFood_shouldThrowResourceNotFoundException_whenFoodDoesNotExists() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", "gr", 100, 100, 200, 50, 370);
        when(foodRepository.existsById(food.getIdFood())).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> foodService.deleteFood(food.getIdFood()));
        verify(foodRepository).existsById(food.getIdFood());
        verify(foodRepository, never()).deleteById(food.getIdFood());


    }
}