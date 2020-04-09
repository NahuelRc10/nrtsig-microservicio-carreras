package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;
import nrtsig.microservicio.carrera.app.models.repository.EstadoCarreraRepository;
import nrtsig.microservicio.carrera.app.services.EstadoCarreraService;

@Service
public class EstadoCarreraServiceImpl extends CommonServiceImpl<EstadoCarrera, EstadoCarreraRepository> implements EstadoCarreraService {

}
