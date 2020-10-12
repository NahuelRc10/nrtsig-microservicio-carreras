package nrtsig.microservicio.carrera.app.models.customrepository;

import java.util.List;

import nrtsig.microservicio.carrera.app.models.dto.*;

public interface SearchRepository {

	public List<Long> searchCarreras(CarreraFiltrosDTO filtrosDTO);
	public List<Long> searchPlanCarrera(PlanCarreraFiltrosDTO filtrosDTO);
	public List<Long> searchInscripcionCarrera(InscripcionCarreraFiltrosDTO filtrosDTO);
	public List<Long> searchComision(ComisionFiltrosDTO filtrosDTO);
	public List<Long> searchAsignaturas(AsignaturaFiltrosDTO filtrosDTO);
	public List<Long> searchInscripcionAsignatura(InscripcionAsignaturaFiltrosDTO filtrosDTO);
}
