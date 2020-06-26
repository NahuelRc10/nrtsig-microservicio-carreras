package nrtsig.microservicio.carrera.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrtsig.microservicio.carrera.app.services.PlanCarreraService;

@RestController
@RequestMapping("/plancarrera")
public class PlanCarreraController extends CommonController<PlanCarrera, PlanCarreraService> {

	private static final Logger logger = LoggerFactory.getLogger(PlanCarreraController.class);
	@Autowired
	private PlanCarreraService planCarreraService;
	
	@GetMapping("/plan-carrera-vigente/{idCarrera}")
	public ResponseEntity<?> obtenerPlanCarreraVigente(@PathVariable Long idCarrera) {
		logger.debug("Ingresa a obtenerPlanCarreraVigente()");
		PlanCarrera planCarrera = planCarreraService.getPlanCarreraActualByCarrera(idCarrera);
		return new ResponseEntity<PlanCarrera>(planCarrera, HttpStatus.OK);
	}
}
