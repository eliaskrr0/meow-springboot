package meow.micromanagerrecipe.client;

import meow.common.dto.FoodDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "food-service", url = "${external.food-service.url}")
public interface FoodClient {
    @GetMapping("api/foods/search")
    List<FoodDTO> searchFoodsByName(@RequestParam("name") String name);

    @GetMapping("api/foods/{id}")
    FoodDTO getFoodById(@RequestParam("id") Long id);
}
