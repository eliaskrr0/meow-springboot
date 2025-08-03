package meow.micromanagerrecipe.service;

import meow.common.dto.FoodDTO;

import java.util.List;

public interface RecipeService {
    List<FoodDTO> searchFoodsByName(String name);
}
