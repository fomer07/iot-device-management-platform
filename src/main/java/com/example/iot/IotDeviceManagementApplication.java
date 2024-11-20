package com.example.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IotDeviceManagementApplication {

	//TODO add to docker hub so can this project can be installed with docker and run, easy to use like kafka just pull and run

	public static void main(String[] args) {
		SpringApplication.run(IotDeviceManagementApplication.class, args);
	}

}
