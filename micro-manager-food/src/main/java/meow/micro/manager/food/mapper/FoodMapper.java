package meow.micro.manager.food.mapper;

import meow.common.dto.diet.food.FoodDTO;
import meow.micro.manager.food.model.Food;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    FoodDTO toDTO(Food food);
    List<FoodDTO> toDTOList(List<Food> foods);
}
