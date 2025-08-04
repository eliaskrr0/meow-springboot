package meow.micromanagerfood.mapper;

import static org.junit.jupiter.api.Assertions.*;

import meow.common.dto.FoodDTO;
import meow.micromanagerfood.model.Food;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

class FoodMapperImplTest {

    private final FoodMapper foodMapper = Mappers.getMapper(FoodMapper.class);

    @Test
    void toDTO_isNotNull_shouldMapFood() {
        // Arrange
        Food food = new Food(1L, "Pollo", "Carnicería", "gr", 100, 20, 0, 5, 120);

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
        Food f1 = new Food(1L, "Pollo", "Carnicería", "gr", 100, 20, 0, 5, 120);
        Food f2 = new Food(2L, "Manzana", "Hacendado", "gr", 80, 0, 22, 0, 88);
        List<Food> foods = List.of(f1, f2);

        // Act
        List<FoodDTO> dtos = foodMapper.toDTOList(foods);

        // Assert
        assertEquals(2, dtos.size());
        assertEquals("Pollo", dtos.get(0).getName());
        assertEquals("Manzana", dtos.get(1).getName());
    }
}
