package meow.micro.manager.recipe.client;

import meow.common.dto.diet.food.FoodDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "food-service", url = "${external.food-service.url}")
public interface FoodClient {
    @GetMapping("api/foods/search")
    List<FoodDTO> searchFoodsByName(@RequestParam("name") String name);

    @GetMapping("/api/foods/{id}")
    FoodDTO getFoodById(@PathVariable("id") Long id);
}
