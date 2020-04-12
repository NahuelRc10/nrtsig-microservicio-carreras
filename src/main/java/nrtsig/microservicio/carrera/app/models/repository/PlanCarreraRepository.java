package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;

public interface PlanCarreraRepository extends PagingAndSortingRepository<PlanCarrera, Long> {

}
