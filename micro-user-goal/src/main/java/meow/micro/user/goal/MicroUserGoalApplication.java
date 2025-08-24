package meow.micro.user.goal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EntityScan({"meow.micro.user.goal", "meow.common.dto.user.goal.enums"})
@EnableFeignClients
@SpringBootApplication
public class MicroUserGoalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroUserGoalApplication.class, args);
	}

}
