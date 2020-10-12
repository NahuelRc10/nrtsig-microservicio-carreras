package nrtsig.microservicio.carrera.app.models.repository;

import nrt.microservicios.main.commons.carrera.entity.DocenteComisionAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocenteComisionAsignaturaRepository extends JpaRepository<DocenteComisionAsignatura, Long> {

    @Query("select dca from DocenteComisionAsignatura dca where dca.comision.id = ?1")
    public List<DocenteComisionAsignatura> findDocenteComisionAsignaturaByIdComision(Long idComision);

    @Query("select dca from DocenteComisionAsignatura dca where dca.comision.id = ?1 and dca.asignatura.id = ?2")
    public List<DocenteComisionAsignatura> findDocenteComisionAsignaturaByIdComisionAndIdAsignatura(Long idComision, Long idAsignatura);

}
