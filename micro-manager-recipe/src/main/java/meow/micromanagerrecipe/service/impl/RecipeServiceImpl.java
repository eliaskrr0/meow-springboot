package meow.micromanagerrecipe.service.impl;

import meow.common.dto.FoodDTO;
import meow.micromanagerrecipe.client.FoodClient;
import meow.micromanagerrecipe.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final FoodClient foodClient;

    public RecipeServiceImpl(FoodClient foodClient) {
        this.foodClient = foodClient;
    }

    @Override
    public List<FoodDTO> searchFoodsByName(String name) {
        return foodClient.sarchFoodsByName(name);
    }
}
