package nrtsig.microservicio.carrera.app.models.customrepository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;

@Component
public class SearchRepositoryImpl implements SearchRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Long> searchCarreras(CarreraFiltrosDTO filtrosDTO) {
		String condition = "WHERE ";
		if (filtrosDTO.getNombre() != null) {
			condition = condition + "car.nombre like '%" + filtrosDTO.getNombre() + "%'";
		}
		if (filtrosDTO.getDuracion() != null) {
			condition = condition + " and car.duracion = " + filtrosDTO.getDuracion();
		}
		if (filtrosDTO.getEstadoCarrera() != null) {
			condition = condition + " and car.carrera_activa = " + filtrosDTO.getEstadoCarrera();
		}
		if (filtrosDTO.getTipoCarrera() != null) {
			condition = condition + " and tipoCar.id = " + filtrosDTO.getTipoCarrera().getId();
		}
		if (filtrosDTO.getDepartamento() != null) {
			condition = condition + " and depart.id = " + filtrosDTO.getDepartamento().getId();
		}
		
		String sqlQuery = "select car.id as id, car.nombre as nombre " + 
						  "from carreras car " + 
						  "inner join tipo_carreras tipoCar on tipoCar.id = car.id_tipo_carrera " + 
						  "inner join departamentos depart on depart.id = car.id_departamento " + 
						  condition;
		System.out.println("QUERY: " + sqlQuery);
		Query query = entityManager.createNativeQuery(sqlQuery);
		@SuppressWarnings({ "unused", "unchecked" })
		List<Object[]> result = query.getResultList();
		List<Long> idList = new ArrayList<Long>();
				
		for (Object[] r : result) {
			try {
				Long id;
				id = r[0] != null ? Long.parseLong(r[0].toString()) : null;
				idList.add(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return idList;
	}

}
