package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.TipoCarrera;
import nrtsig.microservicio.carrera.app.services.TipoCarreraService;

@RestController
@RequestMapping("/tipocarrera")
public class TipoCarreraController extends CommonController<TipoCarrera, TipoCarreraService> {

}
