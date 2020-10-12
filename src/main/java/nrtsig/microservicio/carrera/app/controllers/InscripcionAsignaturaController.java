package nrtsig.microservicio.carrera.app.controllers;

import nrtsig.microservicio.carrera.app.models.dto.InscripcionAsignaturaFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionAsignaturaGroupDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.InscripcionAsignatura;
import nrtsig.microservicio.carrera.app.services.InscripcionAsignaturaService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/inscripcion-asignatura")
public class InscripcionAsignaturaController extends CommonController<InscripcionAsignatura, InscripcionAsignaturaService> {

    private static final Logger logger = LoggerFactory.getLogger(InscripcionAsignaturaController.class);
    @Autowired
    private InscripcionAsignaturaService inscripcionAsignaturaService;

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody InscripcionAsignaturaFiltrosDTO filtrosDTO) {
        logger.debug("Ingresa a search()");
        return ResponseEntity.ok(inscripcionAsignaturaService.searchInscripciones(filtrosDTO));
    }

    @PostMapping("/registrar-inscripcions-group")
    public ResponseEntity<?> registrarInscripcionesAsignatura(@RequestBody InscripcionAsignaturaGroupDTO dto) throws Exception {
        logger.debug("Ingresa a registrarInscripcionesAsignatura()");
        Map<String, Object> response = new HashMap<>();
        Integer rta = null;
        try {
            rta = inscripcionAsignaturaService.registrarInscripciones(dto);
        } catch (Exception e) {
            response.put("error", e.getMessage());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Integer>(rta, HttpStatus.CREATED);
    }
}
