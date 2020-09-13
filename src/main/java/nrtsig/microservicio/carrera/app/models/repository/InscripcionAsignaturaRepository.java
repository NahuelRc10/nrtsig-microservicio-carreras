package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.InscripcionAsignatura;

public interface InscripcionAsignaturaRepository extends PagingAndSortingRepository<InscripcionAsignatura, Long> {

}
