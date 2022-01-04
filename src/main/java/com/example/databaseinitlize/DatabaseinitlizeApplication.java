package com.example.databaseinitlize;

import com.example.databaseinitlize.databasesave.DatabaseInitilization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.example.databaseinitlize"})
@EnableTransactionManagement
public class DatabaseinitlizeApplication  implements CommandLineRunner {


	@Autowired
	private DatabaseInitilization databaseInitialization;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseinitlizeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		databaseInitialization.init();
	}
}
