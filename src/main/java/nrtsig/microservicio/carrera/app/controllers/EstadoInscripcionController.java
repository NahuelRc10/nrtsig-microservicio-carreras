package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;
import nrtsig.microservicio.carrera.app.services.EstadoInscripcionService;

@RestController
@RequestMapping("/estadoinscripcion")
public class EstadoInscripcionController extends CommonController<EstadoInscripcion, EstadoInscripcionService> {

}
