package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.Carrera;

public interface CarreraRepository extends PagingAndSortingRepository<Carrera, Long> {

	@Query(value = "select c.id from carreras c where c.nombre = ?1 and c.nombre_corto = ?2", nativeQuery = true)
	public Long findIdCarreraByNombreAndNombreCorto(String nombre, String nombreCorto);
}
