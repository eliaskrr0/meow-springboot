package meow.micro.manager.food.mapper;

import static org.junit.jupiter.api.Assertions.*;

import meow.common.dto.diet.food.FoodDTO;
import meow.micro.manager.food.model.Food;
import meow.common.dto.diet.food.enums.UnitMeasure;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.math.BigDecimal;

class FoodMapperImplTest {

    private final FoodMapper foodMapper = Mappers.getMapper(FoodMapper.class);

    @Test
    void toDTO_isNotNull_shouldMapFood() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("20.00"), new BigDecimal("0.00"), new BigDecimal("5.00"), new BigDecimal("120.00"), new BigDecimal("1.20"));

        // Act
        FoodDTO dto = foodMapper.toDTO(food);

        // Assert
        assertEquals(food.getIdFood(), dto.getIdFood());
        assertEquals(food.getName(), dto.getName());
        assertEquals(food.getBrand(), dto.getBrand());
    }

    @Test
    void toDTO_whenIsNull_shouldReturnEmptyList() {
        // Arrange & Act
        FoodDTO dto = foodMapper.toDTO(null);

        // Assert
        assertNull(dto);
    }


    @Test
    void toDTOList_whenIsNull_shouldReturnEmptyList() {
        // Arrange & Act
        List<FoodDTO> dtos = foodMapper.toDTOList(null);

        // Assert
        assertNull(dtos);
    }

    @Test
    void toDTOList_isNotNull_shouldMapListOfFood() {
        // Arrange
        Food f1 = new Food(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("20.00"), new BigDecimal("0.00"), new BigDecimal("5.00"), new BigDecimal("120.00"), new BigDecimal("1.20"));
        Food f2 = new Food(2L, "Manzana", "Hacendado", UnitMeasure.GR, 80,
                new BigDecimal("0.00"), new BigDecimal("22.00"), new BigDecimal("0.00"), new BigDecimal("88.00"), new BigDecimal("1.20"));
        List<Food> foods = List.of(f1, f2);

        // Act
        List<FoodDTO> dtos = foodMapper.toDTOList(foods);

        // Assert
        assertEquals(2, dtos.size());
        assertEquals("Pollo", dtos.get(0).getName());
        assertEquals("Manzana", dtos.get(1).getName());
    }

    @Test
    void toEntity_isNotNull_shouldMapDTO() {
        // Arrange
        FoodDTO dto = new FoodDTO(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("20.00"), new BigDecimal("0.00"), new BigDecimal("5.00"), new BigDecimal("120.00"), new BigDecimal("1.20"));

        // Act
        Food food = foodMapper.toEntity(dto);

        // Assert
        assertEquals(dto.getIdFood(), food.getIdFood());
        assertEquals(dto.getName(), food.getName());
        assertEquals(dto.getBrand(), food.getBrand());
    }

    @Test
    void toEntity_whenIsNull_shouldReturnNull() {
        // Arrange & Act
        Food food = foodMapper.toEntity(null);

        // Assert
        assertNull(food);
    }

    @Test
    void toEntityList_whenIsNull_shouldReturnNull() {
        // Arrange & Act
        List<Food> foods = foodMapper.toEntityList(null);

        // Assert
        assertNull(foods);
    }

    @Test
    void toEntityList_isNotNull_shouldMapListOfDTO() {
        // Arrange
        FoodDTO d1 = new FoodDTO(1L, "Pollo", "Carnicería", UnitMeasure.GR, 100,
                new BigDecimal("20.00"), new BigDecimal("0.00"), new BigDecimal("5.00"), new BigDecimal("120.00"), new BigDecimal("1.20"));
        FoodDTO d2 = new FoodDTO(2L, "Manzana", "Hacendado", UnitMeasure.GR, 80,
                new BigDecimal("0.00"), new BigDecimal("22.00"), new BigDecimal("0.00"), new BigDecimal("88.00"), new BigDecimal("1.20"));
        List<FoodDTO> dtos = List.of(d1, d2);

        // Act
        List<Food> foods = foodMapper.toEntityList(dtos);

        // Assert
        assertEquals(2, foods.size());
        assertEquals("Pollo", foods.get(0).getName());
        assertEquals("Manzana", foods.get(1).getName());
    }
}