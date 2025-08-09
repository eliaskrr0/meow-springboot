package meow.micromanagerfood.service.impl;

import meow.micromanagerfood.exception.ResourceNotFoundException;
import meow.micromanagerfood.model.Food;
import meow.micromanagerfood.repository.FoodRepository;
import meow.micromanagerfood.service.FoodService;
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
    public void updateFood(Food food) {
        if (foodRepository.existsById(food.getIdFood())) {
            foodRepository.save(food);
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
