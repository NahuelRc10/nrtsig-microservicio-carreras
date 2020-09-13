package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nrt.microservicios.main.commons.carrera.entity.EstadoAsignatura;

public interface EstadoAsignaturaRepository extends JpaRepository<EstadoAsignatura, Long> {

}
