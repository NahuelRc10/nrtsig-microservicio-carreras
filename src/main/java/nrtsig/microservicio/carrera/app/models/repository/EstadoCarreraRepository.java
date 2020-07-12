package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;

public interface EstadoCarreraRepository extends PagingAndSortingRepository<EstadoCarrera, Long> {

	@Query("select e from EstadoCarrera e where e.codigo = ?1")
	public EstadoCarrera findByCodigo(String codigo);
}
