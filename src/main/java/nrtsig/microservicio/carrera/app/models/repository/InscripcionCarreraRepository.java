package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.InscripcionCarrera;

public interface InscripcionCarreraRepository extends PagingAndSortingRepository<InscripcionCarrera, Long> {

}
