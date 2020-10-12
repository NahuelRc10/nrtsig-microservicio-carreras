package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nrt.microservicios.main.commons.carrera.entity.Comision;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComisionRepository extends JpaRepository<Comision, Long> {

    @Query(value = "select c.id from comisiones c where c.numero_comision = ?1 and c.id_plan_carrera = ?2", nativeQuery = true)
    public Long findIdComisionByNumeroComisionAndIdPlanCarrera(Integer numeroComision, Long idPlanCarrera);

    @Query("select c from Comision c where c.planCarrera.carrera.id = ?1")
    public List<Comision> findComisionesByIdCarrera(Long idCarrera);

    @Query(value = "select * from comisiones com where com.id_plan_carrera = ?1 and com.numero_comision > ?2 and com.numero_comision < ?3 " +
                    " and com.capacidad_actual < com.capacidad_maxima", nativeQuery = true)
    public List<Comision> findComisionesByIdPlanCarreraAndNivelAsignatura(Long idPlanCarrera, Integer comDesde, Integer comHasta);
}
