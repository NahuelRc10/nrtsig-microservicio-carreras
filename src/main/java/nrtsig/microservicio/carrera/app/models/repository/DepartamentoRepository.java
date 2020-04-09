package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import nrt.microservicios.main.commons.carrera.entity.Departamento;

public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {

}
