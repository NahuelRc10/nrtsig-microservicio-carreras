package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;

public interface EstadoInscripcionRepository extends PagingAndSortingRepository<EstadoInscripcion, Long> {

}
