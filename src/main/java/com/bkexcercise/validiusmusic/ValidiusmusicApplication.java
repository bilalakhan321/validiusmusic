package com.bkexcercise.validiusmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ValidiusmusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidiusmusicApplication.class, args);
	}
}
