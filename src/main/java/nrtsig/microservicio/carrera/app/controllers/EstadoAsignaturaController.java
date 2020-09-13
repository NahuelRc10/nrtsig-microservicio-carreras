package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.EstadoAsignatura;
import nrtsig.microservicio.carrera.app.services.EstadoAsignaturaService;

@RestController
@RequestMapping("/estadoasignatura")
public class EstadoAsignaturaController extends CommonController<EstadoAsignatura, EstadoAsignaturaService> {
	
}
