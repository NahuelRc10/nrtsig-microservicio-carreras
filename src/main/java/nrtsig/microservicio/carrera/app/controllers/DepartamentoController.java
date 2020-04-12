package nrtsig.microservicio.carrera.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.Departamento;
import nrtsig.microservicio.carrera.app.services.DepartamentoService;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController extends CommonController<Departamento, DepartamentoService> {

}
