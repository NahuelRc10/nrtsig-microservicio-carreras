package nrtsig.microservicio.carrera.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import nrt.microservicios.main.commons.carrera.entity.AsignaturaCorrelativa;
import nrtsig.microservicio.carrera.app.models.repository.AsignaturaRepository;
import nrtsig.microservicio.carrera.app.services.AsignaturaCorrelativaService;
import nrtsig.microservicio.carrera.app.services.AsignaturaService;
import utils.NRTUtils;

@Service
public class AsignaturaServiceImpl extends CommonServiceImpl<Asignatura, AsignaturaRepository> implements AsignaturaService {

	private static final Logger logger = LoggerFactory.getLogger(AsignaturaServiceImpl.class);
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	@Autowired
	private AsignaturaCorrelativaService asignaturaCorrelativaService;
	
	@Override
	public Iterable<Asignatura> findAll() {
		logger.debug("Ingresa a findAll()");
		List<Asignatura> dtoList = new ArrayList<Asignatura>();
		dtoList = (List<Asignatura>) asignaturaRepository.findAll();
		for (Asignatura a : dtoList) {
			List<AsignaturaCorrelativa> correlativas = new ArrayList<AsignaturaCorrelativa>();
			correlativas = asignaturaCorrelativaService.getCorrelativasByAsignatura(a.getId());
			a.setAsignaturasHijas(correlativas);
		}
		return dtoList;
	}

	@Override
	public Optional<Asignatura> findById(Long id) {
		logger.debug("Ingresa a findById()");
		Optional<Asignatura> o =  asignaturaRepository.findById(id);
		if (!o.isEmpty()) {
			List<AsignaturaCorrelativa> correlativas = new ArrayList<AsignaturaCorrelativa>();
			correlativas = asignaturaCorrelativaService.getCorrelativasByAsignatura(o.get().getId());
			o.get().setAsignaturasHijas(correlativas);
			
		}
		return o;
	}

	@Override
	public Asignatura save(Asignatura entity) throws Exception {
		logger.debug("Ingresa a save()");
		Asignatura asignaturaDb = asignaturaRepository.save(entity);
		if (!NRTUtils.isNullOrEmpty(entity.getAsignaturasHijas())) {
			for (AsignaturaCorrelativa c : entity.getAsignaturasHijas()) {
				c.setIdAsignaturaPrincipal(asignaturaDb.getId());
				asignaturaCorrelativaService.save(c);
			}
		}
		return asignaturaDb;
	}
	
	

}
