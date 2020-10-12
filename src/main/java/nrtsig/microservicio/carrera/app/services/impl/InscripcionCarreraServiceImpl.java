package nrtsig.microservicio.carrera.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nrtsig.microservicio.carrera.app.models.dto.InscripcionAsignaturaGroupDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;
import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;
import nrt.microservicios.main.commons.carrera.entity.InscripcionCarrera;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrt.microservicios.main.commons.usuario.entity.Alumno;
import nrt.microservicios.usuarios.app.services.AlumnoService;
import nrtsig.microservicio.carrera.app.models.customrepository.SearchRepository;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionCarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.repository.InscripcionCarreraRepository;
import nrtsig.microservicio.carrera.app.services.EstadoCarreraService;
import nrtsig.microservicio.carrera.app.services.EstadoInscripcionService;
import nrtsig.microservicio.carrera.app.services.InscripcionCarreraService;
import nrtsig.microservicio.carrera.app.services.PlanCarreraService;

@Service
public class InscripcionCarreraServiceImpl extends CommonServiceImpl<InscripcionCarrera, InscripcionCarreraRepository> implements InscripcionCarreraService {

	private static final Logger logger = LoggerFactory.getLogger(InscripcionCarreraServiceImpl.class);
	@Autowired
	private SearchRepository searchRepository;
	@Autowired
	private InscripcionCarreraRepository inscripcionCarreraRepository;
	@Autowired
	private EstadoCarreraService estadoCarreraService;
	@Autowired
	private EstadoInscripcionService estadoInscripcionService;
	@Autowired
	private PlanCarreraService planCarreraService;
	@Autowired
	private AlumnoService alumnoService;

	@Override
	public InscripcionCarrera save(InscripcionCarrera entity) throws Exception {
		logger.debug("Ingresa a save()");
		InscripcionCarrera inscripcionCarreraDb = new InscripcionCarrera();
		// Validamos que exista el alumno en la base de datos
		if (entity.getAlumno() == null) {
			throw new Exception("La inscripcion debe contener un alumno!");
		}
		Alumno alumno = new Alumno();
		if (entity.getAlumno().getId() != null) {
			Optional<Alumno> alumnoOpt = alumnoService.findById(entity.getAlumno().getId());
			if (alumnoOpt.isEmpty()) {
				throw new Exception("El alumno no existe en la base de datos");
			}
			alumno = alumnoOpt.get();
			inscripcionCarreraDb.setAlumno(alumno);
		} else {
			alumno = alumnoService.save(entity.getAlumno());
			inscripcionCarreraDb.setAlumno(alumno);
		}
		// Asignamos el Estado de Inscripcion ---> Codigo = "PI" ---> Pre-Inscripcion a ser confirmada
		inscripcionCarreraDb.setEstadoInscripcion(estadoInscripcionService.getEstadoInscripcionSegunCodigo(EstadoInscripcion.PRE_INSCRIPCION));
		// Asignamos el Estado de Carrera ---> Codigo = "E" ---> EN ESPERA a ser confirmada
		inscripcionCarreraDb.setEstadoCarrera(estadoCarreraService.getEstadoSegunCodigo(EstadoCarrera.EN_ESPERA));
		// Validamos que el plan de carrera sea el vigente
		PlanCarrera planCarreraActual = planCarreraService.getPlanCarreraActualByCarrera(entity.getPlanCarrera().getCarrera().getId());
		if (entity.getPlanCarrera().equals(planCarreraActual)) {
			throw new Exception("El plan de carrera no es el correcto. Plan desactualizado. No es el plan vigente.");
		}
		inscripcionCarreraDb.setPlanCarrera(entity.getPlanCarrera());
		inscripcionCarreraDb.setFechaInscripcion(entity.getFechaInscripcion());
		inscripcionCarreraDb.setCantidadAsignaturasAprob(0);
		inscripcionCarreraDb.setFechaEgreso(null);
		inscripcionCarreraDb.setNotaPromedio(0.0);
		return inscripcionCarreraRepository.save(inscripcionCarreraDb);
	}

	@Override
	public List<InscripcionCarrera> searchInscripcionesCarrera(InscripcionCarreraFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a searchInscripcionesCarrera()");
		List<Long> idList = searchRepository.searchInscripcionCarrera(filtrosDTO);
		List<InscripcionCarrera> dtoList = new ArrayList<InscripcionCarrera>();
		dtoList = (List<InscripcionCarrera>) inscripcionCarreraRepository.findAllById(idList);
		return dtoList;
	}

	@Override
	public InscripcionCarrera confirmarInscripcionCarrera(Long id) throws Exception {
		logger.debug("Ingresa a confirmarInscripcionCarrera()");
		Optional<InscripcionCarrera> o = inscripcionCarreraRepository.findById(id);
		if (o.isEmpty()) {
			throw new Exception("La inscripcion a confirmar no existe!");
		}
		InscripcionCarrera inscripcionCarrera = o.get();
		// Cambiamos el estado de la inscripcion a CONFIRMADA
		inscripcionCarrera.setEstadoInscripcion(estadoInscripcionService.getEstadoInscripcionSegunCodigo(EstadoInscripcion.CONFIRMADA));
		// Una inscripcion al estar confirmada cambia su estado de carrera a EN_CURSO
		inscripcionCarrera.setEstadoCarrera(estadoCarreraService.getEstadoSegunCodigo(EstadoCarrera.EN_CURSO));
		return inscripcionCarreraRepository.save(inscripcionCarrera);
	}

	@Override
	public InscripcionCarrera reprobarInscripcionCarrera(Long id) throws Exception {
		logger.debug("Ingresa a reprobarInscripcionCarrera()");
		Optional<InscripcionCarrera> o = inscripcionCarreraRepository.findById(id);
		if (o.isEmpty()) {
			throw new Exception("La inscripcion no existe!");
		}
		InscripcionCarrera inscripcionCarrera = o.get();
		// Cambiamos el estado de la inscripcion a NO_APROBADA
		inscripcionCarrera.setEstadoInscripcion(estadoInscripcionService.getEstadoInscripcionSegunCodigo(EstadoInscripcion.NO_APROBADA));
		// Cambiamos el estado de la carrera a LIBRE
		inscripcionCarrera.setEstadoCarrera(estadoCarreraService.getEstadoSegunCodigo(EstadoCarrera.LIBRE));
		return inscripcionCarreraRepository.save(inscripcionCarrera);
	}

	@Override
	public List<InscripcionCarrera> getInscripcionesAlumno(Long idAlumno) {
		logger.debug("Ingresa getInscripcionesAlumno()");
		List<InscripcionCarrera> dtoList = new ArrayList<>();
		return inscripcionCarreraRepository.findInscripcionesByIdAlumno(idAlumno);
	}

}
