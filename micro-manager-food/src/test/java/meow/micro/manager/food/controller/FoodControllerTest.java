package meow.micro.manager.food.controller;

import meow.common.dto.diet.food.FoodDTO;
import meow.micro.manager.food.mapper.FoodMapper;
import meow.micro.manager.food.model.Food;
import meow.common.dto.diet.food.enums.UnitMeasure;
import meow.micro.manager.food.service.FoodService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;
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
        FoodDTO dto1 = new FoodDTO(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("1.20"));
        FoodDTO dto2 = new FoodDTO(1L, "Leche", "Hacendado", UnitMeasure.ML, 250,
                new BigDecimal("17.00"), new BigDecimal("0.00"), new BigDecimal("2.00"), new BigDecimal("112.00"), new BigDecimal("1.20"));
        List<FoodDTO> mockDtos = List.of(dto1, dto2);

        when(foodService.getAllFoods()).thenReturn(mockFoods);
        when(foodMapper.toDTOList(mockFoods)).thenReturn(mockDtos);

        // Act
        ResponseEntity<List<FoodDTO>> response = foodController.getAllFoods();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDtos, response.getBody());
        verify(foodService).getAllFoods();
        verify(foodMapper).toDTOList(mockFoods);
    }

    @Test
    void getFoodById() {
        // Arrange
        Long idFood = 1L;
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("3.20"));
        FoodDTO foodDTO = new FoodDTO(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("3.20"));
        when(foodService.getFoodById(idFood)).thenReturn(food);
        when(foodMapper.toDTO(food)).thenReturn(foodDTO);

        // Act
        ResponseEntity<FoodDTO> response = foodController.getFoodById(idFood);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodDTO, response.getBody());
        verify(foodService).getFoodById(idFood);
        verify(foodMapper).toDTO(food);
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

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dtoList, response.getBody());
        verify(foodService).searchFoodByName(name);
        verify(foodMapper).toDTOList(foods);
    }

    @Test
    void saveFood() {
        // Arrange
        FoodDTO foodDTO = new FoodDTO(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("1.20"));
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("100.00"), new BigDecimal("200.00"), new BigDecimal("50.00"), new BigDecimal("370.00"), new BigDecimal("1.20"));

        when(foodMapper.toEntity(foodDTO)).thenReturn(food);
        when(foodService.saveFood(food)).thenReturn(food);
        when(foodMapper.toDTO(food)).thenReturn(foodDTO);

        // Act
        ResponseEntity<FoodDTO> response = foodController.saveFood(foodDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(foodDTO, response.getBody());
        verify(foodMapper).toEntity(foodDTO);
        verify(foodService).saveFood(food);
        verify(foodMapper).toDTO(food);
    }

    @Test
    void updateFood() {
        // Arrange
        Long idFood = 1L;
        FoodDTO updatedDto = new FoodDTO(null, "Atún", "Calvo", UnitMeasure.GR, 80,
                new BigDecimal("22.00"), new BigDecimal("0.00"), new BigDecimal("3.00"), new BigDecimal("110.00"), new BigDecimal("1.20"));
        Food updatedFood = new Food(null, "Atún", "Calvo", UnitMeasure.GR, 80,
                new BigDecimal("22.00"), new BigDecimal("0.00"), new BigDecimal("3.00"), new BigDecimal("110.00"), new BigDecimal("1.20"));
        FoodDTO resultDto = new FoodDTO(idFood, "Atún", "Calvo", UnitMeasure.GR, 80,
                new BigDecimal("22.00"), new BigDecimal("0.00"), new BigDecimal("3.00"), new BigDecimal("110.00"), new BigDecimal("1.20"));

        when(foodMapper.toEntity(updatedDto)).thenReturn(updatedFood);
        when(foodService.updateFood(updatedFood)).thenReturn(updatedFood);
        when(foodMapper.toDTO(updatedFood)).thenReturn(resultDto);

        // Act
        ResponseEntity<FoodDTO> response = foodController.updateFood(idFood, updatedDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resultDto, response.getBody());
        assertEquals(idFood, updatedFood.getIdFood());
        verify(foodService).updateFood(updatedFood);
        verify(foodMapper).toEntity(updatedDto);
        verify(foodMapper).toDTO(updatedFood);
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

    @Test
    void getUnitMeasures() {
        // Act
        ResponseEntity<List<UnitMeasure>> response = foodController.getUnitMeasures();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(UnitMeasure.values()), response.getBody());
    }
}