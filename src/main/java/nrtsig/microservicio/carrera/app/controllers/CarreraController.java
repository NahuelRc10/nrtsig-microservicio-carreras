package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.Carrera;
import nrtsig.microservicio.carrera.app.services.CarreraService;

@RestController
@RequestMapping("/carrera")
public class CarreraController extends CommonController<Carrera, CarreraService> {

}
