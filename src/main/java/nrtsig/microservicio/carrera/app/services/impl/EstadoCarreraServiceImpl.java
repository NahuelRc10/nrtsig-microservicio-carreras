package nrtsig.microservicio.carrera.app.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;
import nrtsig.microservicio.carrera.app.models.repository.EstadoCarreraRepository;
import nrtsig.microservicio.carrera.app.services.EstadoCarreraService;

@Service
public class EstadoCarreraServiceImpl extends CommonServiceImpl<EstadoCarrera, EstadoCarreraRepository> implements EstadoCarreraService {

	private static Logger logger = LoggerFactory.getLogger(EstadoCarreraServiceImpl.class);
	@Autowired
	private EstadoCarreraRepository estadoCarreraRepository;
	
	@Override
	public EstadoCarrera getEstadoSegunCodigo(String codigo) {
		logger.debug("Ingresa a getEstadoSegunCodigo()");
		return estadoCarreraRepository.findByCodigo(codigo);
	}

}
