package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Carrera;
import nrtsig.microservicio.carrera.app.models.repository.CarreraRepository;
import nrtsig.microservicio.carrera.app.services.CarreraService;

@Service
public class CarreraServiceImpl extends CommonServiceImpl<Carrera, CarreraRepository> implements CarreraService {

}
