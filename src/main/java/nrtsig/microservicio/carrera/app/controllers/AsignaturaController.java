package nrtsig.microservicio.carrera.app.controllers;

import nrtsig.microservicio.carrera.app.models.dto.AsignaturaFiltrosDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import nrtsig.microservicio.carrera.app.services.AsignaturaService;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController extends CommonController<Asignatura, AsignaturaService> {

    private static final Logger logger = LoggerFactory.getLogger(AsignaturaController.class);
    @Autowired
    private AsignaturaService asignaturaService;

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody AsignaturaFiltrosDTO filtrosDTO) {
        logger.debug("Ingresa a search()");
        return new ResponseEntity<List<Asignatura>>(asignaturaService.searchAsignaturasByFiltros(filtrosDTO), HttpStatus.OK);
    }

    @GetMapping("/posibles-correlatividades")
    public ResponseEntity<?> obtenerPosiblesCorrelativas(@RequestParam(name = "nivel") Integer nivel, @RequestParam(name = "idPlanCarrera") Long idPlanCarrera,
                                                         @RequestParam(name = "tipo") Integer tipoAsignatura) {
        logger.debug("Ingresa a obtenerPosiblesCorrelativas()");
        return new ResponseEntity<List<Asignatura>>(asignaturaService.getAsignaturasPosiblesCorrelatividades(nivel, idPlanCarrera, tipoAsignatura), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarAsignatura(@PathVariable Long id, @RequestBody String descripcion) {
        logger.debug("Ingresa a editarAsignatura()");
        asignaturaService.actualizarAsignatura(id, descripcion);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/listar-asignaturas-carrera/{idCarrera}/comision/{idComision}")
    public ResponseEntity<?> obtenerAsignaturaPorCarrera(@PathVariable Long idCarrera, @PathVariable Long idComision, @RequestParam(name = "nroComision") Integer nroComision) {
        logger.debug("Ingresa a obtenerAsignaturaPorCarrera()");
        return new ResponseEntity<List<Asignatura>>(asignaturaService.getAsignaturaByCarrera(idCarrera, idComision, nroComision), HttpStatus.OK);
    }

    @GetMapping("/listar-posibles-asignaturas-inscripcion-alumno/{idAlumno}/{nivel}/{idPlanCarrera}")
    public ResponseEntity<?> listarAsignaturasPosiblesByAlumno(@PathVariable Long idAlumno, @PathVariable Integer nivel, @PathVariable Long idPlanCarrera) {
        logger.debug("Ingresa a listarAsignaturasPosiblesByAlumno()");
        return ResponseEntity.ok(asignaturaService.getAsignaturasPosiblesInscripcionSegunAlumno(idAlumno, nivel, idPlanCarrera));
    }

    @GetMapping("/listar-asignaturas-segun-nivel-plancarrera/{nivel}/{idPlanCarrera}")
    public ResponseEntity<?> listarAsignaturasPosiblesSegunNivelAndPlan(@PathVariable Integer nivel, @PathVariable Long idPlanCarrera) {
        logger.debug("Ingresa a listarAsignaturasPosiblesSegunNivelAndPlan()");
        return ResponseEntity.ok(asignaturaService.getAsignaturasSegunNivelAndPlanCarrera(nivel, idPlanCarrera));
    }
}
