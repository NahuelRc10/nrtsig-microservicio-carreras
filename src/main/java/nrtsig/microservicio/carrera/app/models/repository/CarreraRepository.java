package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.Carrera;

public interface CarreraRepository extends PagingAndSortingRepository<Carrera, Long> {

}
