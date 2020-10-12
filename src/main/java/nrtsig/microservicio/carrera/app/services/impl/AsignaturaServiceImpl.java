package nrtsig.microservicio.carrera.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nrt.microservicios.main.commons.carrera.entity.DocenteComisionAsignatura;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrtsig.microservicio.carrera.app.models.customrepository.SearchRepository;
import nrtsig.microservicio.carrera.app.models.dto.AsignaturaFiltrosDTO;
import nrtsig.microservicio.carrera.app.services.DocenteComisionAsignaturaService;
import nrtsig.microservicio.carrera.app.services.PlanCarreraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import nrt.microservicios.main.commons.carrera.entity.AsignaturaCorrelativa;
import nrtsig.microservicio.carrera.app.models.repository.AsignaturaRepository;
import nrtsig.microservicio.carrera.app.services.AsignaturaCorrelativaService;
import nrtsig.microservicio.carrera.app.services.AsignaturaService;
import utils.NRTUtils;

@Service
public class AsignaturaServiceImpl extends CommonServiceImpl<Asignatura, AsignaturaRepository> implements AsignaturaService {

	private static final Logger logger = LoggerFactory.getLogger(AsignaturaServiceImpl.class);
	@Autowired
	private AsignaturaRepository asignaturaRepository;
	@Autowired
	private SearchRepository searchRepository;
	@Autowired
	private AsignaturaCorrelativaService asignaturaCorrelativaService;
	@Autowired
	private PlanCarreraService planCarreraService;
	@Autowired
	private DocenteComisionAsignaturaService docenteComisionAsignaturaService;

	@Override
	public Iterable<Asignatura> findAll() {
		logger.debug("Ingresa a findAll()");
		List<Asignatura> dtoList = new ArrayList<Asignatura>();
		dtoList = (List<Asignatura>) asignaturaRepository.findAll();
		for (Asignatura a : dtoList) {
			List<AsignaturaCorrelativa> correlativas = new ArrayList<AsignaturaCorrelativa>();
			correlativas = asignaturaCorrelativaService.getCorrelativasByAsignatura(a.getId());
			a.setAsignaturasHijas(correlativas);
		}
		return dtoList;
	}

	@Override
	public Optional<Asignatura> findById(Long id) {
		logger.debug("Ingresa a findById()");
		Optional<Asignatura> o =  asignaturaRepository.findById(id);
		if (!o.isEmpty()) {
			List<AsignaturaCorrelativa> correlativas = new ArrayList<AsignaturaCorrelativa>();
			correlativas = asignaturaCorrelativaService.getCorrelativasByAsignatura(o.get().getId());
			o.get().setAsignaturasHijas(correlativas);
			
		}
		return o;
	}

	@Override
	public Asignatura save(Asignatura entity) throws Exception {
		logger.debug("Ingresa a save()");
		// Validamos que el Plan de Carrera sea el actual para la carrera
		PlanCarrera planCarreraActual = planCarreraService.getPlanCarreraActualByCarrera(entity.getPlanCarrera().getCarrera().getId());
		if (entity.getPlanCarrera().equals(planCarreraActual)) {
			throw new Exception("El Plan Carrera no esta vigente");
		}

		// Validamos que la asignatura no exista para la carrera seleccionada
		Long id = asignaturaRepository.getIdCarreraByNombreAsignaturaAndPlanCarrera(entity.getNombre(), entity.getPlanCarrera().getId());
		if (id != null) {
			throw new Exception("La Asignatura ya existe para la carrera seleccionada");
		}

		// Validamos que el tipoAsignatura sea valido
		if (entity.getTipoAsignatura() != 1 && entity.getTipoAsignatura() != 2) {
			throw new Exception("El tipo de asignatura no es valido");
		}

		Asignatura asignaturaDb = asignaturaRepository.save(entity);
		if (!NRTUtils.isNullOrEmpty(entity.getAsignaturasHijas())) {
			for (AsignaturaCorrelativa c : entity.getAsignaturasHijas()) {
				c.setIdAsignaturaPrincipal(asignaturaDb.getId());
				asignaturaCorrelativaService.save(c);
			}
		}
		return asignaturaDb;
	}


	@Override
	public List<Asignatura> searchAsignaturasByFiltros(AsignaturaFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a searchAsignaturasByFiltros()");
		List<Long> idList = searchRepository.searchAsignaturas(filtrosDTO);
		List<Asignatura> dtoList = new ArrayList<>();
		return (List<Asignatura>) asignaturaRepository.findAllById(idList);
	}

	@Override
	public List<Asignatura> getAsignaturasPosiblesCorrelatividades(Integer nivel, Long idPlanCarrera, Integer tipoAsignatura) {
		logger.debug("Ingresa a getAsignaturasPosiblesCorrelatividades()");
		List<Asignatura> asignaturas = new ArrayList<>();
		if (tipoAsignatura == 1) {
			return asignaturaRepository.findAsignaturasLessThanNivelAndByPlanCarreraAndTipoObligatoria(nivel, idPlanCarrera, tipoAsignatura);
		}
		return asignaturaRepository.findAsignaturasLessThanNivelAndByPlanCarrera(nivel, idPlanCarrera);
	}

	@Override
	public void actualizarAsignatura(Long id, String descripcion) {
		logger.debug("Ingresa a actualizarAsignatura()");
		// Por reglas de negocio solo se puede actualizar la descripcion de la
		// asignatura, los demas campos no se pueden modificar
		if (descripcion != null) {
			asignaturaRepository.actualizarDescripcionAsignatura(descripcion, id);
		}
	}

	@Override
	public void deleteById(Long id) {
		logger.debug("Ingresa a deleteById()");
		// Primero eliminamos las correlativas correspondientes a la asignatura
		asignaturaCorrelativaService.borrarCorrelativasSegunAsignatura(id);
		super.deleteById(id);
	}

	@Override
	public List<Asignatura> getAsignaturaByCarrera(Long idCarrera, Long idComision, Integer nroComision) {
		logger.debug("Ingresa a getAsignaturaByCarrera()");
		// Primero obtenemos todas las asignaturas correspondiente a la carrera
		List<Asignatura> asignaturasCarrera = new ArrayList<>();
		Integer nivel = Integer.parseInt(nroComision.toString().substring(0,1));
		asignaturasCarrera = asignaturaRepository.findAsignaturasByIdCarrera(idCarrera, nivel);
		// Obtenemos los objetos DocenteComisionAsignatura ya registrados para esa comision
		List<DocenteComisionAsignatura> comisionAsignaturasRegistradas = new ArrayList<>();
		comisionAsignaturasRegistradas = docenteComisionAsignaturaService.getDocenteComisionAsignaturaByIdComision(idComision);
		if (!NRTUtils.isNullOrEmpty(comisionAsignaturasRegistradas)) {
			// Validamos que la asignatura no aparezca mas de 3 veces para la misma comision
			// Si aparece 3 veces significa que esa asignatura para esa comision ya tiene todas las funciones docente cubiertas
			// por lo tanto no es necesario mostrarla como una opcion a elegir nuevamente
			int contador = 0;
			for (DocenteComisionAsignatura dca : comisionAsignaturasRegistradas) {
				if (asignaturasCarrera.contains(dca.getAsignatura())) {
					contador++;
				}
				if (contador == 3) {
					// Eliminamos la asignatura de la lista y reseteamos el contador
					asignaturasCarrera.remove(dca.getAsignatura());
					contador = 0;
				}
			}
		}
		return asignaturasCarrera;
	}

	@Override
	public List<Asignatura> getAsignaturasPosiblesInscripcionSegunAlumno(Long idAlumno, Integer nivel, Long idPlanCarrera) {
		logger.debug("Ingresa a getAsignaturasPosiblesInscripcionSegunAlumno()");
		return asignaturaRepository.findAsignaturasByIdAlumnoAndNivelAndIdPlanCarrera(idAlumno, nivel, idPlanCarrera);
	}

	@Override
	public List<Asignatura> getAsignaturasSegunNivelAndPlanCarrera(Integer nivel, Long idPlanCarrera) {
		logger.debug("Ingresa a getAsignaturasSegunNivelAndPlanCarrera()");
		return asignaturaRepository.findAsignaturasByNivelAndIdPlanCarrera(nivel, idPlanCarrera);
	}


}
