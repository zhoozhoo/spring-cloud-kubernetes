package ca.zhoozhoo.spring.cloud.kubernetes.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication
public class AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}
}
