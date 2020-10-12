package nrtsig.microservicio.carrera.app.services.impl;

import nrt.microservicios.main.commons.carrera.entity.EstadoAsignatura;
import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;
import nrt.microservicios.main.commons.carrera.entity.InscripcionCarrera;
import nrt.microservicios.main.commons.usuario.entity.Alumno;
import nrtsig.microservicio.carrera.app.models.customrepository.SearchRepository;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionAsignaturaFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionAsignaturaGroupDTO;
import nrtsig.microservicio.carrera.app.services.EstadoAsignaturaService;
import nrtsig.microservicio.carrera.app.services.EstadoCarreraService;
import nrtsig.microservicio.carrera.app.services.InscripcionCarreraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.InscripcionAsignatura;
import nrtsig.microservicio.carrera.app.models.repository.InscripcionAsignaturaRepository;
import nrtsig.microservicio.carrera.app.services.InscripcionAsignaturaService;
import utils.NRTUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InscripcionAsignaturaServiceImpl extends CommonServiceImpl<InscripcionAsignatura, InscripcionAsignaturaRepository> implements InscripcionAsignaturaService {

    private static final Logger logger = LoggerFactory.getLogger(InscripcionAsignaturaServiceImpl.class);
    @Autowired
    private InscripcionAsignaturaRepository inscripcionAsignaturaRepository;
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private EstadoAsignaturaService estadoAsignaturaService;
    @Autowired
    private EstadoCarreraService estadoCarreraService;
    @Autowired
    private InscripcionCarreraService inscripcionCarreraService;

    @Override
    public InscripcionAsignatura save(InscripcionAsignatura entity) throws Exception {
        logger.debug("Ingresa a save()");
        // Iniciamos el estado y la nota
        entity.setEstadoAsignatura(estadoAsignaturaService.getEstadoAsignaturaByCodigo(EstadoAsignatura.REGULAR));
        entity.setNota(0.0);

        // Validamos que el alumno no se haya inscripto a la asignatura en el actual ciclo lectivo
        Integer anioActual = Calendar.YEAR;
        Long idAlumno = entity.getAlumno().getId();
        Long idAsignatura = entity.getAsignatura().getId();
        InscripcionAsignatura inscripcionAsignaturaPrevia = inscripcionAsignaturaRepository.findInscripcionAsignaturaByAlumnoAndAsignaturaAndAnio(idAlumno, idAsignatura, anioActual);
        if (inscripcionAsignaturaPrevia != null) {
            throw new Exception("El alumno " + entity.getAlumno().getApellido() + ", " + entity.getAlumno().getNombre() + " ya se ha inscripto a esta asignatura este año!");
        }
        return inscripcionAsignaturaRepository.save(entity);
    }

    @Override
    public List<InscripcionAsignatura> searchInscripciones(InscripcionAsignaturaFiltrosDTO filtrosDTO) {
        logger.debug("Ingresa a searchInscripciones()");
        List<Long> idList = searchRepository.searchInscripcionAsignatura(filtrosDTO);
        return (List<InscripcionAsignatura>) inscripcionAsignaturaRepository.findAllById(idList);
    }

    @Override
    public Integer registrarInscripciones(InscripcionAsignaturaGroupDTO dto) throws Exception {
        logger.debug("Ingresa a registrarInscripciones()");
        List<Alumno> alumnosInscriptosPreviamente = new ArrayList<>();
        List<Alumno> alumnosNoInscriptosCarreraAsignatura = new ArrayList<>();
        // Hacemos un bucle para registrar la inscripcion de cada alumno
        for (Alumno a : dto.getAlumnos()) {
            InscripcionAsignatura inscripcion = new InscripcionAsignatura();
            inscripcion.setAlumno(a);
            inscripcion.setComision(dto.getComision());
            inscripcion.setAsignatura(dto.getAsignatura());
            inscripcion.setPlanCarrera(dto.getPlanCarrera());
            inscripcion.setEstadoAsignatura(estadoAsignaturaService.getEstadoAsignaturaByCodigo(EstadoAsignatura.REGULAR));
            inscripcion.setNota(0.0);

            // Validamos que el alumno este inscripto a la carrera correspondiente a la asignatura seleccionada;
            List<InscripcionCarrera> carrerasInscriptasAlumno = inscripcionCarreraService.getInscripcionesAlumno(a.getId());
            if (!NRTUtils.isNullOrEmpty(carrerasInscriptasAlumno)) {
                int counter = 0;
                for (InscripcionCarrera ic : carrerasInscriptasAlumno) {
                    counter++;
                    // Validamos que el estado de la inscripcion sea En curso
                    if (ic.getEstadoCarrera().equals(estadoCarreraService.getEstadoSegunCodigo(EstadoCarrera.EN_CURSO))) {
                        if (!dto.getPlanCarrera().equals(ic.getPlanCarrera()) && counter == carrerasInscriptasAlumno.size()) {
                            alumnosNoInscriptosCarreraAsignatura.add(a);
                        }
                    }
                }
            }

            // Validamos que el alumno no se haya inscripto a la asignatura en el actual ciclo lectivo
            Calendar calendar = Calendar.getInstance();
            Integer anioActual = calendar.get(Calendar.YEAR);
            Long idAlumno = a.getId();
            Long idAsignatura = dto.getAsignatura().getId();
            InscripcionAsignatura inscripcionAsignaturaPrevia = inscripcionAsignaturaRepository.findInscripcionAsignaturaByAlumnoAndAsignaturaAndAnio(idAlumno, idAsignatura, anioActual);
            if (inscripcionAsignaturaPrevia != null) {
                alumnosInscriptosPreviamente.add(a);
            } else {
                inscripcionAsignaturaRepository.save(inscripcion);
            }
        }

        if (!NRTUtils.isNullOrEmpty(alumnosInscriptosPreviamente)) {
            String mensajeError = "Los alumnos: ";
            for (Alumno a : alumnosInscriptosPreviamente) {
                mensajeError = mensajeError + a.getApellido() + ", " + a.getNombre() + " - ";
            }
            mensajeError = mensajeError + " ya se han inscripto a esta asignatura este año.\n";

            if (!NRTUtils.isNullOrEmpty(alumnosNoInscriptosCarreraAsignatura)) {
                for (Alumno a : alumnosNoInscriptosCarreraAsignatura) {
                    mensajeError = mensajeError + " " + a.getApellido() + ", " + a.getNombre() + " - ";
                }
                mensajeError = mensajeError + " no estan inscriptos a la carrera de la asignatura";
            }

            if (!mensajeError.isEmpty()) {
                throw new Exception(mensajeError);
            }
        }

        return dto.getAlumnos().size();
    }
}
