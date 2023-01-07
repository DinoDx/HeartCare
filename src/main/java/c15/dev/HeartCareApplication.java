package c15.dev;

import c15.dev.utils.DBPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HeartCareApplication {
	@Autowired
	DBPopulator populator;

	public static void main(String[] args) {
		SpringApplication.run(HeartCareApplication.class, args);
	}

}
