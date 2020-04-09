package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;

public interface EstadoInscripcionRepository extends CrudRepository<EstadoInscripcion, Long> {

}
