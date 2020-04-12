package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.TipoCarrera;
import nrtsig.microservicio.carrera.app.models.repository.TipoCarreraRepository;
import nrtsig.microservicio.carrera.app.services.TipoCarreraService;

@Service
public class TipoCarreraServiceImpl extends CommonServiceImpl<TipoCarrera, TipoCarreraRepository> implements TipoCarreraService {

}
