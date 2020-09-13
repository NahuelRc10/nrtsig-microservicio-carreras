package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.InscripcionAsignatura;
import nrtsig.microservicio.carrera.app.models.repository.InscripcionAsignaturaRepository;
import nrtsig.microservicio.carrera.app.services.InscripcionAsignaturaService;

@Service
public class InscripcionAsignaturaServiceImpl extends CommonServiceImpl<InscripcionAsignatura, InscripcionAsignaturaRepository> implements InscripcionAsignaturaService {

}
