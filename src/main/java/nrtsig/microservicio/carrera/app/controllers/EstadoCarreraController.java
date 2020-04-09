package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;
import nrtsig.microservicio.carrera.app.services.EstadoCarreraService;

@RestController
@RequestMapping("/estadocarrera")
public class EstadoCarreraController extends CommonController<EstadoCarrera, EstadoCarreraService> {

}
