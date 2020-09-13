package nrtsig.microservicio.carrera.app.services.impl;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.DocenteComisionAsignatura;
import nrtsig.microservicio.carrera.app.models.repository.DocenteComisionAsignaturaRepository;
import nrtsig.microservicio.carrera.app.services.DocenteComisionAsignaturaService;
import org.springframework.stereotype.Service;

@Service
public class DocenteComisionAsignaturaServiceImpl extends CommonServiceImpl<DocenteComisionAsignatura, DocenteComisionAsignaturaRepository> implements DocenteComisionAsignaturaService {
}
