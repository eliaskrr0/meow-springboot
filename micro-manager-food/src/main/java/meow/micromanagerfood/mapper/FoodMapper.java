package meow.micromanagerfood.mapper;

import meow.common.dto.FoodDTO;
import meow.micromanagerfood.model.Food;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    FoodDTO toDTO(Food food);
    List<FoodDTO> toDTOList(List<Food> foods);
}
