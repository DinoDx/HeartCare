package heartcare;

import heartcare.utils.DBPopulator;
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
