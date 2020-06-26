package nrtsig.microservicio.carrera.app.services;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;

public interface PlanCarreraService extends CommonService<PlanCarrera> {

	public PlanCarrera getPlanCarreraActualByCarrera(Long idCarrera);
}
