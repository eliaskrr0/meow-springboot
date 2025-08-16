package meow.micro.manager.food.service.impl;

import meow.micro.manager.food.exception.ResourceNotFoundException;
import meow.micro.manager.food.model.Food;
import meow.common.dto.diet.food.enums.UnitMeasure;
import meow.micro.manager.food.repository.FoodRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
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
        Food food1 = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
        Food food2 = new Food(2L, "Leche", "Hacendado", UnitMeasure.ML, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
        List<Food> mockFoods = List.of(food1, food2);

        when(foodRepository.findAll()).thenReturn(mockFoods);

        // Act
        List<Food> result = foodService.getAllFoods();

        // Assert
        assertEquals(mockFoods, result);
    }

    @Test
    void getFoodById() {
        // Arrange
        Long idFood = 1L;
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));

        when(foodRepository.findById(idFood)).thenReturn(Optional.of(food));

        // Act
        Food result = foodService.getFoodById(idFood);

        //Assert
        assertEquals(food, result);
        verify(foodRepository, times(1)).findById(idFood);
    }

    @Test
    void searchFoodByName() {
        // Arrange
        Food food1 = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
        Food food2 = new Food(2L, "Leche", "Hacendado", UnitMeasure.ML, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
        List<Food> mockFoods = List.of(food1, food2);
        String name = "pollo";

        when(foodRepository.findByNameContainingIgnoreCase(name)).thenReturn(mockFoods);

        // Act
        List<Food> result = foodService.searchFoodByName(name);

        // Assert
        assertEquals(mockFoods, result);
    }

    @Test
    void updateFood_shouldUpdate_whenFoodExists() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
        when(foodRepository.existsById(food.getIdFood())).thenReturn(true);
        when(foodRepository.save(food)).thenReturn(food);

        // Act
        Food result = foodService.updateFood(food);

        // Assert
        assertEquals(food, result);
        verify(foodRepository).existsById(food.getIdFood());
        verify(foodRepository).save(food);
    }

    @Test
    void updateFood_shouldThrowResourceNotFoundException_whenFoodDoesNotExists() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
        when(foodRepository.existsById(food.getIdFood())).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> foodService.updateFood(food));
        verify(foodRepository).existsById(food.getIdFood());
        verify(foodRepository, never()).save(food);


    }

    @Test
    void deleteFood_shouldDelete_whenFoodExists() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
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
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100, new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("50"), new BigDecimal("370"), new BigDecimal("1.20"));
        when(foodRepository.existsById(food.getIdFood())).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> foodService.deleteFood(food.getIdFood()));
        verify(foodRepository).existsById(food.getIdFood());
        verify(foodRepository, never()).deleteById(food.getIdFood());


    }
}