package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.EstadoAsignatura;
import nrtsig.microservicio.carrera.app.models.repository.EstadoAsignaturaRepository;
import nrtsig.microservicio.carrera.app.services.EstadoAsignaturaService;

@Service
public class EstadoAsignaturaServiceImpl extends CommonServiceImpl<EstadoAsignatura, EstadoAsignaturaRepository> implements EstadoAsignaturaService {

}
