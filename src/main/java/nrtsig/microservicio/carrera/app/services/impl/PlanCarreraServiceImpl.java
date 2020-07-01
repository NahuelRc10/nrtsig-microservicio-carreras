package nrtsig.microservicio.carrera.app.services.impl;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrtsig.microservicio.carrera.app.models.repository.PlanCarreraRepository;
import nrtsig.microservicio.carrera.app.services.PlanCarreraService;

@Service
public class PlanCarreraServiceImpl extends CommonServiceImpl<PlanCarrera, PlanCarreraRepository> implements PlanCarreraService {

	private static final Logger logger = LoggerFactory.getLogger(PlanCarreraServiceImpl.class);
	@Autowired
	private PlanCarreraRepository planCarreraRepository;
	
	@Override
	public PlanCarrera getPlanCarreraActualByCarrera(Long idCarrera) {
		logger.debug("Ingresa a getPlanCarreraActualByCarrera");
		return planCarreraRepository.findPlanCarreraActualByCarrera(idCarrera);
	}

	@Override
	public void cerrarPlanCarreraById(Long id) throws Exception {
		Optional<PlanCarrera> o = planCarreraRepository.findById(id);
		if (o.isEmpty()) {
			throw new Exception("No existe el Plan Carrera");
		}
		
		PlanCarrera planCarrera = o.get();
		planCarrera.setFechaCierre(new Date());
		planCarreraRepository.save(planCarrera);
	}
	
}
