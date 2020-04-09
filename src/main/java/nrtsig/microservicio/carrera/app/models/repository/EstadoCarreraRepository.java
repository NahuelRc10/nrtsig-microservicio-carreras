package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;

public interface EstadoCarreraRepository extends CrudRepository<EstadoCarrera, Long> {

}
