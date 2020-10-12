package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.InscripcionCarrera;

import java.util.List;

public interface InscripcionCarreraRepository extends PagingAndSortingRepository<InscripcionCarrera, Long> {

    @Query("select ic from InscripcionCarrera ic where ic.alumno.id = ?1")
    public List<InscripcionCarrera> findInscripcionesByIdAlumno(Long idAlumno);
}
