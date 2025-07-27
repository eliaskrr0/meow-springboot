package meow.micromanagerfood.service.impl;

import meow.micromanagerfood.exception.ResourceNotFoundException;
import meow.micromanagerfood.model.Food;
import meow.micromanagerfood.repository.FoodRepository;
import meow.micromanagerfood.service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {
    private FoodRepository foodRepository;

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
    public void updateFood(Food food) {
        if (foodRepository.existsById(food.getId())) {
            foodRepository.save(food);
        } else {
            throw new ResourceNotFoundException(food.getId());
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
