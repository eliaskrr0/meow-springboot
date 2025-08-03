package meow.micromanagerrecipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroManagerRecipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroManagerRecipeApplication.class, args);
	}

}
