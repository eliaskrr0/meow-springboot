package meow.micro.user.goal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroUserGoalApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroUserGoalApplication.class, args);
	}

}
