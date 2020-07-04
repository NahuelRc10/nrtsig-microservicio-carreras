package nrtsig.microservicio.carrera.app.models.customrepository;

import java.util.List;

import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.PlanCarreraFiltrosDTO;

public interface SearchRepository {

	public List<Long> searchCarreras(CarreraFiltrosDTO filtrosDTO);
	public List<Long> searchPlanCarrera(PlanCarreraFiltrosDTO filtrosDTO);
}
