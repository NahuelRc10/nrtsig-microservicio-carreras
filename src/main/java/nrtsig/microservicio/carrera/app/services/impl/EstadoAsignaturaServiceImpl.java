package nrtsig.microservicio.carrera.app.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.EstadoAsignatura;
import nrtsig.microservicio.carrera.app.models.repository.EstadoAsignaturaRepository;
import nrtsig.microservicio.carrera.app.services.EstadoAsignaturaService;

@Service
public class EstadoAsignaturaServiceImpl extends CommonServiceImpl<EstadoAsignatura, EstadoAsignaturaRepository> implements EstadoAsignaturaService {

    private static final Logger logger = LoggerFactory.getLogger(EstadoAsignaturaServiceImpl.class);
    @Autowired
    private EstadoAsignaturaRepository estadoAsignaturaRepository;

    @Override
    public EstadoAsignatura getEstadoAsignaturaByCodigo(String codigo) {
        return estadoAsignaturaRepository.findByCodigo(codigo);
    }
}
