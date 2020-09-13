package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.AsignaturaCorrelativa;
import nrtsig.microservicio.carrera.app.services.AsignaturaCorrelativaService;

@RestController
@RequestMapping("/correlativa")
public class AsignaturaCorrelativaController extends CommonController<AsignaturaCorrelativa, AsignaturaCorrelativaService> {

}
