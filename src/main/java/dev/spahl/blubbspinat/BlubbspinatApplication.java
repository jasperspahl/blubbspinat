package dev.spahl.blubbspinat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BlubbspinatApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(BlubbspinatApplication.class)
				.build()
				.run(args);
	}

}
