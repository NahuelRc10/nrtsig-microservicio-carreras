package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;

public interface PlanCarreraRepository extends PagingAndSortingRepository<PlanCarrera, Long> {

	@Query("select pc from PlanCarrera pc where pc.carrera.id = ?1 and pc.fechaCierre is null")
	public PlanCarrera findPlanCarreraActualByCarrera(Long idCarrera);
}
