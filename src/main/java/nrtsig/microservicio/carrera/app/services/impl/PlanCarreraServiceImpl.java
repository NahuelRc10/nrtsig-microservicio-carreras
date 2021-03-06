package nrtsig.microservicio.carrera.app.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrtsig.microservicio.carrera.app.models.customrepository.SearchRepository;
import nrtsig.microservicio.carrera.app.models.dto.PlanCarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.repository.PlanCarreraRepository;
import nrtsig.microservicio.carrera.app.services.PlanCarreraService;
import utils.NRTUtils;

@Service
public class PlanCarreraServiceImpl extends CommonServiceImpl<PlanCarrera, PlanCarreraRepository> implements PlanCarreraService {

	private static final Logger logger = LoggerFactory.getLogger(PlanCarreraServiceImpl.class);
	@Autowired
	private PlanCarreraRepository planCarreraRepository;
	@Autowired
	private SearchRepository searchRepository;
	
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

	@Override
	public List<PlanCarrera> search(PlanCarreraFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a search()");
		List<Long> idList = searchRepository.searchPlanCarrera(filtrosDTO);
		List<PlanCarrera> dtoList = new ArrayList<PlanCarrera>();
		dtoList = (List<PlanCarrera>) planCarreraRepository.findAllById(idList);
		return dtoList;
	}

	@Override
	public PlanCarrera save(PlanCarrera entity) throws Exception {
		logger.debug("Ingresa a save()");
		// Validaciones antes de persistir en la Base de Datos
		if (NRTUtils.cadContainsLetters(entity.getAnioPlan().toString())) {
			throw new Exception("El año del plan no puede contener letras. Solo numeros");
		}
		// Validamos que no haya ningun plan abierto para la carrera que le corresponde
		List<Long> idListPlanVigente = new ArrayList<Long>();
		idListPlanVigente = planCarreraRepository.findPlanCarreraVigenteByIdCarrera(entity.getCarrera().getId());
		if (!NRTUtils.isNullOrEmpty(idListPlanVigente)) {
			throw new Exception("La carrera seleccionada ya tiene un plan vigente.");
		}
		// Validamos que el año del plan sea mayor al año del ultimo plan de la carrera seleccionada
		Integer lastAnioPlan = planCarreraRepository.findLastAnioPlanByIdCarrera(entity.getCarrera().getId());
		if (entity.getAnioPlan() < lastAnioPlan) {
			throw new Exception("El año del plan no puede ser menor al año del ultimo plan de la carrera seleccionada");
		}
		return planCarreraRepository.save(entity);
	}
	
}
