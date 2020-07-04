package nrtsig.microservicio.carrera.app.services;

import java.util.List;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrtsig.microservicio.carrera.app.models.dto.PlanCarreraFiltrosDTO;

public interface PlanCarreraService extends CommonService<PlanCarrera> {

	public PlanCarrera getPlanCarreraActualByCarrera(Long idCarrera);
	public void cerrarPlanCarreraById(Long id) throws Exception;
	public List<PlanCarrera> search(PlanCarreraFiltrosDTO filtrosDTO);
}
