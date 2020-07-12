package nrtsig.microservicio.carrera.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import nrt.microservicios.main.commons.carrera.entity.Carrera;

public interface CarreraRepository extends PagingAndSortingRepository<Carrera, Long> {

	@Query(value = "select c.id from carreras c where c.nombre = ?1 and c.nombre_corto = ?2", nativeQuery = true)
	public Long findIdCarreraByNombreAndNombreCorto(String nombre, String nombreCorto);
	
	@Modifying
	@Transactional
	@Query(value = "update carreras set carrera_activa = false where id = ?1", nativeQuery = true)
	public void updateCarreraActiva(Long idCarrera);
	
	@Query("select c from Carrera c order by c.nombre asc")
	public List<Carrera> findCarrerasOrderByNombre();
	
	@Query("select c from Carrera c where c.departamento.id = ?1 order by c.nombre asc")
	public List<Carrera> findCarreraByDepartamentoOrderByNombre(Long idDepartamento);
	
	@Query(value = "select * from carreras c where c.id_tipo_carrera = ?1", nativeQuery = true)
	public List<Carrera> findCarreraByTipoCarrera(Long idTipoCarrera);
}
