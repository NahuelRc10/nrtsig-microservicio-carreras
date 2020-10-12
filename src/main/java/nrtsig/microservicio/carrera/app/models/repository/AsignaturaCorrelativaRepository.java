package nrtsig.microservicio.carrera.app.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nrt.microservicios.main.commons.carrera.entity.AsignaturaCorrelativa;

public interface AsignaturaCorrelativaRepository extends JpaRepository<AsignaturaCorrelativa, Long> {

	@Query("select a from AsignaturaCorrelativa a where a.idAsignaturaPrincipal = ?1")
	public List<AsignaturaCorrelativa> findByIdAsignaturaPrincipal(Long idAsignaturaPrincipal);

	@Query(value = "delete from correlativas where id_asignatura = ?1", nativeQuery = true)
	public Long deleteCorrelativasByIdAsignatura(Long idAsignatura);

}
