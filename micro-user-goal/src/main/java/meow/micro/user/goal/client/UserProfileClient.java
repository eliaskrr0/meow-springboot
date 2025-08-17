package meow.micro.user.goal.client;

import meow.common.dto.user.profile.UserProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-profile-service", url = "${external.user-profile-service.url}")
public interface UserProfileClient {
    @GetMapping("/api/user/profile/{id}")
    UserProfileDTO getProfile(@PathVariable("id") Long id);
}
