package nrtsig.microservicio.carrera.app.controllers;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.DocenteComisionAsignatura;
import nrtsig.microservicio.carrera.app.services.DocenteComisionAsignaturaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docente-comision-controller")
public class DocenteComisionAsignaturaController extends CommonController<DocenteComisionAsignatura, DocenteComisionAsignaturaService> {
}
