package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;

public interface EstadoCarreraRepository extends PagingAndSortingRepository<EstadoCarrera, Long> {

}
