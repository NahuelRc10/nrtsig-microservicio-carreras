package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;

public interface EstadoInscripcionRepository extends PagingAndSortingRepository<EstadoInscripcion, Long> {

	@Query("select e from EstadoInscripcion e where e.codigo = ?1")
	public EstadoInscripcion findByCodigo(String codigo);
}
