package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;
import nrtsig.microservicio.carrera.app.models.repository.EstadoInscripcionRepository;
import nrtsig.microservicio.carrera.app.services.EstadoInscripcionService;

@Service
public class EstadoInscripcionServiceImpl extends CommonServiceImpl<EstadoInscripcion, EstadoInscripcionRepository> implements EstadoInscripcionService {

}
