package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import nrtsig.microservicio.carrera.app.services.AsignaturaService;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController extends CommonController<Asignatura, AsignaturaService> {
	
}
