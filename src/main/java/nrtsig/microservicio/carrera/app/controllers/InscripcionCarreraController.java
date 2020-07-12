package nrtsig.microservicio.carrera.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.InscripcionCarrera;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionCarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.services.InscripcionCarreraService;

@RestController
@RequestMapping("/inscripcioncarrera")
public class InscripcionCarreraController extends CommonController<InscripcionCarrera, InscripcionCarreraService> {

	private static final Logger logger = LoggerFactory.getLogger(InscripcionCarreraController.class);
	@Autowired
	private InscripcionCarreraService inscripcionCarreraService;
	
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody InscripcionCarreraFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a search()");
		List<InscripcionCarrera> dtoList = new ArrayList<InscripcionCarrera>();
		dtoList = inscripcionCarreraService.searchInscripcionesCarrera(filtrosDTO);
		return new ResponseEntity<List<InscripcionCarrera>>(dtoList, HttpStatus.OK);
	}
	
	@PutMapping("/confirmar-inscripcion/{id}")
	public ResponseEntity<?> confirmarInscripcion(@PathVariable Long id) {
		logger.debug("Ingresa a confirmarInscripcion()");
		InscripcionCarrera inscripcionCarrera = new InscripcionCarrera();
		try {
			inscripcionCarrera = inscripcionCarreraService.confirmarInscripcionCarrera(id);
			return new ResponseEntity<InscripcionCarrera>(inscripcionCarrera, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/no-aprobar-inscripcion/{id}")
	public ResponseEntity<?> denegarInscripcion(@PathVariable Long id) {
		logger.debug("Ingresa a denegarInscripcion()");
		InscripcionCarrera inscripcionCarrera = new InscripcionCarrera();
		try {
			inscripcionCarrera = inscripcionCarreraService.reprobarInscripcionCarrera(id);
			return new ResponseEntity<InscripcionCarrera>(inscripcionCarrera, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
