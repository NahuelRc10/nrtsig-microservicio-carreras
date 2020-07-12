package nrtsig.microservicio.carrera.app.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;
import nrtsig.microservicio.carrera.app.models.repository.EstadoInscripcionRepository;
import nrtsig.microservicio.carrera.app.services.EstadoInscripcionService;

@Service
public class EstadoInscripcionServiceImpl extends CommonServiceImpl<EstadoInscripcion, EstadoInscripcionRepository> implements EstadoInscripcionService {

	private static final Logger logger = LoggerFactory.getLogger(EstadoInscripcionServiceImpl.class);
	@Autowired
	private EstadoInscripcionRepository estadoInscripcionRepository;
	
	@Override
	public EstadoInscripcion getEstadoInscripcionSegunCodigo(String codigo) {
		logger.debug("Ingresa a getEstadoInscripcionSegunCodigo()");
		return estadoInscripcionRepository.findByCodigo(codigo);
	}

}
