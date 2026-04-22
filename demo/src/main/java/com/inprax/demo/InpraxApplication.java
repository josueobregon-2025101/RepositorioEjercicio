package com.inprax.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InpraxApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(InpraxApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Api funcionando");
    }
}
