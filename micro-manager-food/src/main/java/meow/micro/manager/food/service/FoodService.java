package meow.micro.manager.food.service;

import meow.micro.manager.food.model.Food;

import java.util.List;

public interface FoodService {
    Food saveFood(Food food);
    List<Food> getAllFoods();
    Food getFoodById(Long id);
    List<Food> searchFoodByName(String name);
    Food updateFood(Food food);
    void deleteFood(Long id);
}
