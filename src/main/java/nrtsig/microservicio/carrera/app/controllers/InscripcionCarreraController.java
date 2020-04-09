package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.InscripcionCarrera;
import nrtsig.microservicio.carrera.app.services.InscripcionCarreraService;

@RestController
@RequestMapping("/inscripcioncarrera")
public class InscripcionCarreraController extends CommonController<InscripcionCarrera, InscripcionCarreraService> {

}
