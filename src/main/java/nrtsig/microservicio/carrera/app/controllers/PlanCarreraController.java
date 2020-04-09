package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrtsig.microservicio.carrera.app.services.PlanCarreraService;

@RestController
@RequestMapping("/plancarrera")
public class PlanCarreraController extends CommonController<PlanCarrera, PlanCarreraService> {

}
