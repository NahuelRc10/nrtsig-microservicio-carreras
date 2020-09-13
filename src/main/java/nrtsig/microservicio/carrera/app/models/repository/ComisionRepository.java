package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nrt.microservicios.main.commons.carrera.entity.Comision;
import org.springframework.data.jpa.repository.Query;

public interface ComisionRepository extends JpaRepository<Comision, Long> {

    @Query(value = "select c.id from comisiones c where c.numero_comision = ?1 and c.id_plan_carrera = ?2", nativeQuery = true)
    public Long findIdComisionByNumeroComisionAndIdPlanCarrera(Integer numeroComision, Long idPlanCarrera);
}
