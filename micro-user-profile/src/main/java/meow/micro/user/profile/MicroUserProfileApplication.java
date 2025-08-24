package meow.micro.user.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"meow.micro.user.profile", "meow.common.dto.user.profile.enums"})
@SpringBootApplication
public class MicroUserProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroUserProfileApplication.class, args);
	}

}
