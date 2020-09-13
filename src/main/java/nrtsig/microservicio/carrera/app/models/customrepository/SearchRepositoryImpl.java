package nrtsig.microservicio.carrera.app.models.customrepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.ComisionFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionCarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.PlanCarreraFiltrosDTO;

@Component
public class SearchRepositoryImpl implements SearchRepository {

	private static final Logger logger = LoggerFactory.getLogger(SearchRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Long> searchCarreras(CarreraFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a searchCarreras()");
		String condition = "WHERE TRUE";
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

	@Override
	public List<Long> searchPlanCarrera(PlanCarreraFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a searchPlanCarrera()");
		String condition = "WHERE TRUE";
		if (filtrosDTO.getAnioPlanDesde() != null) {
			condition = condition + "plan.anio_plan >= " + filtrosDTO.getAnioPlanDesde();
		}
		if (filtrosDTO.getAnioPlanHasta() != null) {
			condition = condition + " and plan.anio_plan <= " + filtrosDTO.getAnioPlanHasta();
		}
		if (filtrosDTO.getCarrera() != null) {
			condition = condition + " and carrera.id = " + filtrosDTO.getCarrera().getId();
		}
		if (filtrosDTO.getTipoCarrera() != null) {
			condition = condition + " and tipoCar.id = " + filtrosDTO.getTipoCarrera().getId();
		}
		if (filtrosDTO.getDpto() != null) {
			condition = condition + " and dpto.id = " + filtrosDTO.getDpto().getId();
		}
		
		String sqlQuery = "select plan.id, plan.anio_plan " + 
						  "from plan_carreras plan " + 
						  "inner join carreras carrera on carrera.id = plan.id_carrera " + 
						  "inner join departamentos dpto on dpto.id = plan.id_departamento " + 
						  "inner join tipo_carreras tipoCar on tipoCar.id = carrera.id_tipo_carrera " + 
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

	@Override
	public List<Long> searchInscripcionCarrera(InscripcionCarreraFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a searchInscripcionCarrera()");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String condition = "WHERE TRUE";
		String fechaInscripcionDesde = filtrosDTO.getFechaInscripcionDesde() != null ? formato.format(filtrosDTO.getFechaInscripcionDesde()) : null;
		String fechaInscripcionHasta = filtrosDTO.getFechaInscripcionHasta() != null ? formato.format(filtrosDTO.getFechaInscripcionHasta()) : null;
		
		if (filtrosDTO.getNombreCarrera() != null) {
			condition = condition + " and car.nombre like '%" + filtrosDTO.getNombreCarrera() + "%' ";
		}
		if (filtrosDTO.getEstadoCarrera() != null) {
			condition = condition + " and estCarrera.id = " + filtrosDTO.getEstadoCarrera().getId();
		}
		if (filtrosDTO.getEstadoInscripcion() != null) {
			condition = condition + " and estInscrip.id = " + filtrosDTO.getEstadoInscripcion().getId();
		}
		if (filtrosDTO.getCantAsignaturaAprobDesde() != null) {
			condition = condition + " and insCar.cantidad_asignaturas > " + filtrosDTO.getCantAsignaturaAprobDesde();
		}
		if (filtrosDTO.getCantAsignaturaAprobHasta() != null) {
			condition = condition + " and insCar.cantidad_asignaturas < " + filtrosDTO.getCantAsignaturaAprobHasta();
		}
		if (fechaInscripcionDesde != null) {
			condition = condition + " and insCar.fecha_inscripcion > '" + fechaInscripcionDesde + "'";
		}
		if (fechaInscripcionHasta != null) {
			condition = condition + " and insCar.fecha_inscripcion < '" + fechaInscripcionHasta + "'";
		}
		if (filtrosDTO.getNombreAlumno() != null) {
			condition = condition + " and alumno.nombre like '%" + filtrosDTO.getNombreAlumno() + "%'";
		}
		if (filtrosDTO.getApellidoAlumno() != null) {
			condition = condition + " and alumno.apellido like '%" + filtrosDTO.getApellidoAlumno() + "%'";
		}
		if (filtrosDTO.getDepartamento() != null) {
			condition = condition + " and dpto.id = " + filtrosDTO.getDepartamento().getId();
		}

		String sqlQuery = "select insCar.id, insCar.fecha_inscripcion " + 
						  "from inscripcion_carrera insCar " + 
						  "inner join estados_carrera estCarrera on estCarrera.id = insCar.id_estado " + 
						  "inner join estados_inscripcion estInscrip on estInscrip.id = insCar.id_estado_inscripcion " + 
						  "inner join plan_carreras planCar on planCar.id = insCar.id_plan_carrera " + 
						  "inner join carreras car on car.id = planCar.id_carrera " + 
						  "inner join departamentos dpto on dpto.id = car.id_departamento " + 
						  "inner join alumnos alumno on alumno.id = insCar.id_alumno " + 
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

	@Override
	public List<Long> searchComision(ComisionFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a searchComision()");
		String condition = "WHERE TRUE";
		if (filtrosDTO.getNumeroComision() != null) {
			condition = condition + " and com.numero_comision = " + filtrosDTO.getNumeroComision();
		}
		if (filtrosDTO.getCarrera() != null) {
			condition = condition + " and carrera.id = " + filtrosDTO.getCarrera().getId();
		}
		
		String sqlQuery = "select com.id, com.numero_comision " + 
				          "from comisiones com " + 
				          "inner join plan_carreras plan on plan.id = com.id_plan_carrera " + 
				          "inner join carreras carrera on carrera.id = plan.id_carrera " + 
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
