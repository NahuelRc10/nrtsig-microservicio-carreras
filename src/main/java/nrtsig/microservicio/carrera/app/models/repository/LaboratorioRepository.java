package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nrt.microservicios.main.commons.carrera.entity.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

}
