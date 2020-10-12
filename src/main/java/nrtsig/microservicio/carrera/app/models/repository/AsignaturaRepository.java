package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AsignaturaRepository extends PagingAndSortingRepository<Asignatura, Long> {

    @Query(value = "select asig.id " +
                   "from asignaturas asig " +
                   "inner join plan_carreras plan on plan.id = asig.id_plan_carrera " +
                   "where asig.nombre = ?1 and plan.id = ?2 ;", nativeQuery = true)
    public Long getIdCarreraByNombreAsignaturaAndPlanCarrera(String nombre, Long id);

    @Query("select a from Asignatura a where a.nivel < ?1 and a.planCarrera.id = ?2 and a.tipoAsignatura = ?3 order by a.nombre")
    public List<Asignatura> findAsignaturasLessThanNivelAndByPlanCarreraAndTipoObligatoria(Integer nivel, Long idPlanCarrera, Integer tipoAsignatura);

    @Query("select a from Asignatura a where a.nivel < ?1 and a.planCarrera.id = ?2 order by a.nombre")
    public List<Asignatura> findAsignaturasLessThanNivelAndByPlanCarrera(Integer nivel, Long idPlanCarrera);

    @Modifying
    @Transactional
    @Query(value = "update asignaturas set descripcion = ?1 where id = ?2", nativeQuery = true)
    public void actualizarDescripcionAsignatura(String descripcion, Long id);

    @Query(value = "select asignatura.* " +
                   "from asignaturas asignatura " +
                   "inner join plan_carreras plan on plan.id = asignatura.id_plan_carrera and plan.fecha_cierre is null " +
                   "inner join carreras carrera on carrera.id = plan.id_carrera " +
                   "where id_carrera = ?1 and asignatura.nivel = ?2 ", nativeQuery = true)
    public List<Asignatura> findAsignaturasByIdCarrera(Long idCarrera, Integer nivel);

    @Query(value = "select * " +
                   "from asignaturas asig " +
                   "where asig.id not in (select inscrip.id_asignatura from inscripcion_asignatura inscrip where inscrip.id_alumno = ?1) " +
                   "and asig.nivel = ?2 and asig.id_plan_carrera = ?3 ", nativeQuery = true)
    public List<Asignatura> findAsignaturasByIdAlumnoAndNivelAndIdPlanCarrera(Long idAlumno, Integer nivel, Long idPlanCarrera);

    @Query(value = "select * " +
            "from asignaturas asig " +
            "where asig.nivel = ?1 and asig.id_plan_carrera = ?2 ", nativeQuery = true)
    public List<Asignatura> findAsignaturasByNivelAndIdPlanCarrera(Integer nivel, Long idPlanCarrera);
}
