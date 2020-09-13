package nrtsig.microservicio.carrera.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(scanBasePackages={"nrtsig.microservicio.carrera.app", "nrt.microservicios.usuarios.app"})
@EntityScan({"nrt.microservicios.main.commons.usuario.entity",
			 "nrt.microservicios.main.commons.carrera.entity"})
public class NrtsigMicroservicioCarrerasApplication {

	public static void main(String[] args) {
		SpringApplication.run(NrtsigMicroservicioCarrerasApplication.class, args);
	}

}
