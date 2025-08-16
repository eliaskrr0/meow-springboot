package meow.micro.manager.food.service.impl;

import meow.micro.manager.food.exception.ResourceNotFoundException;
import meow.micro.manager.food.model.Food;
import meow.micro.manager.food.repository.FoodRepository;
import meow.micro.manager.food.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    @Override
    public Food getFoodById(Long id) {
        return foodRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public List<Food> searchFoodByName(String name) {
        return foodRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Food updateFood(Food food) {
        if (foodRepository.existsById(food.getIdFood())) {
            return foodRepository.save(food);
        } else {
            throw new ResourceNotFoundException(food.getIdFood());
        }
    }

    @Override
    public void deleteFood(Long id) {
        if (foodRepository.existsById(id)) {
            foodRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}
