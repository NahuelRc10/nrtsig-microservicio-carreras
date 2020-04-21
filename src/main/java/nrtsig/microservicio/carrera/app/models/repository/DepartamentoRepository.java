package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.Departamento;

public interface DepartamentoRepository extends PagingAndSortingRepository<Departamento, Long> {

}
