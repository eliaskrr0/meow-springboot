package meow.micromanagerfood.service;

import meow.micromanagerfood.model.Food;

import java.util.List;

public interface FoodService {
    Food saveFood(Food food);
    List<Food> getAllFoods();
    List<Food> searchFoodByName(String name);
    void updateFood(Food food);
    void deleteFood(Long id);
}
