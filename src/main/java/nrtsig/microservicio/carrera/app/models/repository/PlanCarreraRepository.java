package nrtsig.microservicio.carrera.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;

public interface PlanCarreraRepository extends PagingAndSortingRepository<PlanCarrera, Long> {

	@Query("select pc from PlanCarrera pc where pc.carrera.id = ?1 and pc.fechaCierre is null")
	public PlanCarrera findPlanCarreraActualByCarrera(Long idCarrera);
	
	@Query(value = "select plan.id from plan_carreras plan where plan.fecha_cierre is null and plan.id_carrera = ?1", nativeQuery = true)
	public List<Long> findPlanCarreraVigenteByIdCarrera(Long idCarrera);
	
	@Query(value = "select max(plan.anio_plan) from plan_carreras where plan.anio_plan < year(now()) and plan.id_carrera = ?", nativeQuery = true)
	public Integer findLastAnioPlanByIdCarrera(Long idCarrera);
}


 