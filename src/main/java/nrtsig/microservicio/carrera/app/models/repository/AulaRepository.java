package nrtsig.microservicio.carrera.app.models.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nrt.microservicios.main.commons.carrera.entity.Aula;

import java.util.List;

public interface AulaRepository extends PagingAndSortingRepository<Aula, Long> {

    @Query(value = "select a.numero_salon from aulas a", nativeQuery = true)
    public List<Integer> findNumeroSalon();

    @Query("select a from Aula a where a.comision.id = ?1")
    public List<Aula> findAulaByIdComision(Long idComision);

    @Modifying
    @Query(value = "update aulas a set a.id_comision = null where a.id_comision = ?1", nativeQuery = true)
    public Integer updateIdComision(Long idComision);

    @Modifying
    @Query(value = "update aulas a set a.id_comision = null where a.id = ?1", nativeQuery = true)
    public Integer updateAulaById(Long id);

    @Query("select a from Aula a where a.numeroSalon = ?1")
    public Aula findAulaByNumeroSalon(Integer numeroSalon);
}
