package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;

public interface PlanCarreraRepository extends CrudRepository<PlanCarrera, Long> {

}
