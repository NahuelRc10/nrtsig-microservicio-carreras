package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.InscripcionCarrera;
import nrtsig.microservicio.carrera.app.models.repository.InscripcionCarreraRepository;
import nrtsig.microservicio.carrera.app.services.InscripcionCarreraService;

@Service
public class InscripcionCarreraServiceImpl extends CommonServiceImpl<InscripcionCarrera, InscripcionCarreraRepository> implements InscripcionCarreraService {

}
