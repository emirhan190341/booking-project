package com.emirhanarici.bookingproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(JdbcConnectionDetails jdbc) {
		return args -> {
			System.out.println("buradayim");
			System.out.println(jdbc.getClass().getName());
		};
	}

}
