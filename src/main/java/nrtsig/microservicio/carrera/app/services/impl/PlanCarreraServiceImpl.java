package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrtsig.microservicio.carrera.app.models.repository.PlanCarreraRepository;
import nrtsig.microservicio.carrera.app.services.PlanCarreraService;

@Service
public class PlanCarreraServiceImpl extends CommonServiceImpl<PlanCarrera, PlanCarreraRepository> implements PlanCarreraService {

	@Autowired
	private PlanCarreraRepository planCarreraRepository;
	
	@Override
	public PlanCarrera getPlanCarreraActualByCarrera(Long idCarrera) {
		return planCarreraRepository.findPlanCarreraActualByCarrera(idCarrera);
	}

}
