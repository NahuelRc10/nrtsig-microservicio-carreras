package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.Laboratorio;
import nrtsig.microservicio.carrera.app.services.LaboratorioService;

@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController extends CommonController<Laboratorio, LaboratorioService> {

}
