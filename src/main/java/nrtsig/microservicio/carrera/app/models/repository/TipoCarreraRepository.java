package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.TipoCarrera;

public interface TipoCarreraRepository extends PagingAndSortingRepository<TipoCarrera, Long> {

}
