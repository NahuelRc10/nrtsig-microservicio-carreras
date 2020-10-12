package nrtsig.microservicio.carrera.app.controllers;

import nrt.microservicios.commons.controllers.CommonController;
import nrt.microservicios.main.commons.carrera.entity.DocenteComisionAsignatura;
import nrtsig.microservicio.carrera.app.services.DocenteComisionAsignaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docente-comision-controller")
public class DocenteComisionAsignaturaController extends CommonController<DocenteComisionAsignatura, DocenteComisionAsignaturaService> {

    private static final Logger logger = LoggerFactory.getLogger(DocenteComisionAsignaturaController.class);
    @Autowired
    private DocenteComisionAsignaturaService docenteComisionAsignaturaService;

    @GetMapping("/mostrar-label/{idComision}/{idAsignatura}")
    public ResponseEntity<?> getDocenteComisionAsignaturaByComisionAndByAsignatura(@PathVariable Long idComision, @PathVariable Long idAsignatura) {
        logger.debug("Ingresa a getDocenteComisionAsignaturaByComisionAndByAsignatura()");
        return new ResponseEntity<String>(docenteComisionAsignaturaService.getLabelDocenteComisionAsignaturaByComisionAndAsignatura(idComision, idAsignatura), HttpStatus.OK);
    }
}
