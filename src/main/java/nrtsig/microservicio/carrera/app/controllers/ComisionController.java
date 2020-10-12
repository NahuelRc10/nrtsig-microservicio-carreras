package nrtsig.microservicio.carrera.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.Comision;
import nrtsig.microservicio.carrera.app.models.dto.ComisionFiltrosDTO;
import nrtsig.microservicio.carrera.app.services.ComisionService;

@RestController
@RequestMapping("/comision")
public class ComisionController extends CommonController<Comision, ComisionService> {
	
	private static final Logger logger = LoggerFactory.getLogger(ComisionController.class);
	@Autowired
	private ComisionService comisionService;
	
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody ComisionFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a search()");
		List<Comision> list = new ArrayList<Comision>();
		list = comisionService.search(filtrosDTO);
		return new ResponseEntity<List<Comision>>(list, HttpStatus.OK);
	}

	@GetMapping("/valida-existencia-nrocomision-carrera")
	public ResponseEntity<?> validaExistenciaNroComisionCarrera(@RequestParam(value = "id_plan") Long idPlan, @RequestParam(value = "nroComision") Integer nroComision) {
		logger.debug("Ingresa a validaExistenciaNroComisionCarrera()");
		boolean flag = comisionService.existComisionParaCarrera(idPlan, nroComision);
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateComision(@PathVariable Long id, @RequestBody Comision comision) throws Exception {
		logger.debug("Ingresa a updateComision()");
		Comision comisionDb = comisionService.updateComision(id, comision);
		if (comisionDb == null) {
			return new ResponseEntity<Comision>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Comision>(comisionDb, HttpStatus.CREATED);
	}

	@GetMapping("/listar-comisiones-carrera/{idCarrera}")
	public ResponseEntity<?> listarComisionesByCarrera(@PathVariable Long idCarrera) {
		logger.debug("Ingresa a listarComisionesByCarrera()");
		return new ResponseEntity<List<Comision>>(comisionService.getComisionesByIdCarrera(idCarrera), HttpStatus.OK);
	}

	@GetMapping("/comisiones-posibles-asignatura/{idPlanCarrera}")
	public ResponseEntity<?> listarComisionesSegunPlanAndNivelAsignatura(@PathVariable Long idPlanCarrera, @RequestParam(name = "nivel") Integer nivel) {
		logger.debug("Ingresa a listarComisionesSegunPlanAndNivelAsignatura()");
		return ResponseEntity.ok(comisionService.getComisionesByPlanCarreraAndNivelAsignatura(idPlanCarrera, nivel));
	}
}
