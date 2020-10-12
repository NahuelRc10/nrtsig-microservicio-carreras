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
import nrt.microservicios.main.commons.carrera.entity.Carrera;
import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.services.CarreraService;

@RestController
@RequestMapping("/carrera")
public class CarreraController extends CommonController<Carrera, CarreraService> {

	private static final Logger logger = LoggerFactory.getLogger(CarreraController.class);
	@Autowired
	private CarreraService carreraService;
	
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody CarreraFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a search()");
		List<Carrera> carreras = new ArrayList<Carrera>();
		carreras = carreraService.search(filtrosDTO);
		return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
	}
	
	@PutMapping("/{id}/activar-carrera")
	public ResponseEntity<?> activarCarrera(@PathVariable Long id) {
		logger.debug("Ingresa a activarCarrera()");
		try {
			carreraService.activarCarrera(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Carrera carrera) throws Exception {
		logger.debug("Ingresa a editar()");
		Carrera carreraDb = carreraService.actualizarCarrera(carrera, id);
		return new ResponseEntity<Carrera>(carreraDb, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}/desactivar-carrera")
	public ResponseEntity<?> desactivaCarrera(@PathVariable Long id) {
		logger.debug("Ingresa a desactivaCarrera()");
		carreraService.desactivarCarrera(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/listar-carreras-ordenadas")
	public ResponseEntity<?> obtenerCarrerasOrdenadasPorNombre() {
		logger.debug("Ingresa a obtenerCarreraOrdenadasPorNombre()");
		List<Carrera> carreras = carreraService.getCarrerasOrdenadasByNombre();
		return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
	}
	
	@GetMapping("/listar-carrera-por-departamento/{idDepartamento}")
	public ResponseEntity<?> obtenerCarrerasByDepartamento(@PathVariable Long idDepartamento){
		logger.debug("Ingresar a obtenerCarrerasByDepartamento()");
		List<Carrera> carreras = carreraService.getCarreraByDepartamento(idDepartamento);
		return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
	}
	
	@GetMapping("/listar-carrera-por-tipo/{idTipoCarrera}")
	public ResponseEntity<?> obtenerCarrerasByTipoCarrera(@PathVariable Long idTipoCarrera) {
		logger.debug("Ingresa a obtenerCarrerasByTipoCarrera()");
		List<Carrera> carreras = carreraService.getCarrerasByTipoCarrera(idTipoCarrera);
		return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
	}
	
	@GetMapping("/listar-carrera-por-tipo-por-alumno/{idTipoCarrera}/{idAlumno}")
	public ResponseEntity<?> obtenerCarrerasByTipoCarreraAndAlumno(@PathVariable Long idTipoCarrera, @PathVariable Long idAlumno) {
		logger.debug("Ingresa a obtenerCarrerasByTipoCarreraAndAlumno()");
		List<Carrera> carreras = carreraService.getCarrerasByTipoCarreraAndByAlumno(idAlumno, idTipoCarrera);
		return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
	}

	@GetMapping("/listar-carrera-por-duracion")
	public ResponseEntity<?> obtenerCarrerasByDuracion(@RequestParam(name = "duracion") Integer duracion) {
		logger.debug("Ingresa a obtenerCarrerasByDuracion()");
		return new ResponseEntity<List<Carrera>>(carreraService.getCarrerasByDuracion(duracion), HttpStatus.OK);
	}

	@GetMapping("/listar-carreras-inscriptas-segun-alumno/{idAlumno}")
	public ResponseEntity<?> obtenerCarrerasInscriptasSegunAlumno(@PathVariable Long idAlumno) {
		logger.debug("Ingresa a obtenerCarrerasInscriptasSegunAlumno()");
		return new ResponseEntity<List<Carrera>>(carreraService.getCarrerasAlumnoInscripto(idAlumno), HttpStatus.OK);
	}
}
