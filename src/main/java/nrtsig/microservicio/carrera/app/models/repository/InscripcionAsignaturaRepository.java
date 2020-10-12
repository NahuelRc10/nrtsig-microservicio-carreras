package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.InscripcionAsignatura;

public interface InscripcionAsignaturaRepository extends PagingAndSortingRepository<InscripcionAsignatura, Long> {

    @Query(value = "select * from inscripcion_asignatura i " +
                   "where i.id_alumno = ?1 and i.id_asignatura = ?2 and year(i.fecha_inscripcion) = ?3 ", nativeQuery = true)
    public InscripcionAsignatura findInscripcionAsignaturaByAlumnoAndAsignaturaAndAnio(Long idAlumno, Long idAsignatura, Integer anio);
}
