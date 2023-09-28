package user.userauthtoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("user.userauthtoken.usermapper")
public class UserAuthTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthTokenApplication.class, args);
	}

}
