package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.InscripcionAsignatura;
import nrtsig.microservicio.carrera.app.services.InscripcionAsignaturaService;

@RestController
@RequestMapping("/inscripcion-asignatura")
public class InscripcionAsignaturaController extends CommonController<InscripcionAsignatura, InscripcionAsignaturaService> {

}
