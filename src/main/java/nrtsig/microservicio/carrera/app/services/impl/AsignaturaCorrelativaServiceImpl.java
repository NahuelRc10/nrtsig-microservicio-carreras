package nrtsig.microservicio.carrera.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.AsignaturaCorrelativa;
import nrtsig.microservicio.carrera.app.models.repository.AsignaturaCorrelativaRepository;
import nrtsig.microservicio.carrera.app.services.AsignaturaCorrelativaService;

@Service
public class AsignaturaCorrelativaServiceImpl extends CommonServiceImpl<AsignaturaCorrelativa, AsignaturaCorrelativaRepository> implements AsignaturaCorrelativaService {

	private static final Logger logger = LoggerFactory.getLogger(AsignaturaCorrelativaServiceImpl.class);
	@Autowired
	private AsignaturaCorrelativaRepository asignaturaCorrelativaRepository;
	
	@Override
	public List<AsignaturaCorrelativa> getCorrelativasByAsignatura(Long idAsignatura) {
		logger.debug("Ingresa a getCorrelativasByAsignatura()");
		List<AsignaturaCorrelativa> dtoList = new ArrayList<AsignaturaCorrelativa>();
		dtoList = asignaturaCorrelativaRepository.findByIdAsignaturaPrincipal(idAsignatura);
		return dtoList;
	}

	@Override
	public void borrarCorrelativasSegunAsignatura(Long idAsignatura) {
		logger.debug("Ingresa a borrarCorrelativasSegunAsignatura()");
		asignaturaCorrelativaRepository.deleteCorrelativasByIdAsignatura(idAsignatura);
	}

}
