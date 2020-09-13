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
import nrt.microservicios.main.commons.carrera.entity.Aula;
import nrtsig.microservicio.carrera.app.services.AulaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aula")
public class AulaController extends CommonController<Aula, AulaService> {

    private static final Logger logger = LoggerFactory.getLogger(AulaController.class);
    @Autowired
    private AulaService aulaService;

    @GetMapping("/obtener-numero-salon")
    public ResponseEntity<?> obtenerNumeroSalon() {
        logger.debug("Ingresa a obtenerNumeroSalon()");
        Integer numero = aulaService.getNumeroSalon();
        return new ResponseEntity<Integer>(numero, HttpStatus.OK);
    }

    @GetMapping("/aula-comision/{idComision}")
    public ResponseEntity<?> obtenerAulasComision(@PathVariable Long idComision) {
        logger.debug("Ingresa a obtenerAulasComision()");
        List<Aula> aulaList = new ArrayList<>();
        aulaList = aulaService.getAulaByComision(idComision);
        return new ResponseEntity<List<Aula>>(aulaList, HttpStatus.OK);
    }

    @GetMapping("/liberar-aula/{id}")
    public ResponseEntity<?> liberarAula(@PathVariable Long id) {
        logger.debug("Ingresa a liberarAula()");
        aulaService.liberarAulaByIdComision(id, null);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
