package nrtsig.microservicio.carrera.app.models.customrepository;

import java.util.List;

import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.ComisionFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionCarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.PlanCarreraFiltrosDTO;

public interface SearchRepository {

	public List<Long> searchCarreras(CarreraFiltrosDTO filtrosDTO);
	public List<Long> searchPlanCarrera(PlanCarreraFiltrosDTO filtrosDTO);
	public List<Long> searchInscripcionCarrera(InscripcionCarreraFiltrosDTO filtrosDTO);
	public List<Long> searchComision(ComisionFiltrosDTO filtrosDTO);
}
