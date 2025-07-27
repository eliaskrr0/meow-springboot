package meow.micromanagerfood.service;

import meow.micromanagerfood.model.Food;

import java.util.List;

public interface FoodService {
    Food saveFood(Food food);
    List<Food> getAllFoods();
    void updateFood(Food food);
    void deleteFood(Long id);
}
