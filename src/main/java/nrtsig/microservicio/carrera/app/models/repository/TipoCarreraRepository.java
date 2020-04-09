package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import nrt.microservicios.main.commons.carrera.entity.TipoCarrera;

public interface TipoCarreraRepository extends CrudRepository<TipoCarrera, Long> {

}
