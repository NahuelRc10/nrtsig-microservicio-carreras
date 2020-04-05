package nrtsig.microservicio.carrera.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NrtsigMicroservicioCarrerasApplication {

	public static void main(String[] args) {
		SpringApplication.run(NrtsigMicroservicioCarrerasApplication.class, args);
	}

}
