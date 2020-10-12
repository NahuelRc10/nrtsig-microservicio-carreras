package nrtsig.microservicio.carrera.app.services.impl;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.DocenteComisionAsignatura;
import nrtsig.microservicio.carrera.app.models.repository.DocenteComisionAsignaturaRepository;
import nrtsig.microservicio.carrera.app.services.DocenteComisionAsignaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.NRTUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocenteComisionAsignaturaServiceImpl extends CommonServiceImpl<DocenteComisionAsignatura, DocenteComisionAsignaturaRepository> implements DocenteComisionAsignaturaService {

    private static final Logger logger = LoggerFactory.getLogger(DocenteComisionAsignaturaServiceImpl.class);
    @Autowired
    private DocenteComisionAsignaturaRepository docenteComisionAsignaturaRepository;

    @Override
    public List<DocenteComisionAsignatura> getDocenteComisionAsignaturaByIdComision(Long idComision) {
        logger.debug("Ingresa a getDocenteComisionAsignaturaByIdComision()");
        List<DocenteComisionAsignatura> list = new ArrayList<>();
        return docenteComisionAsignaturaRepository.findDocenteComisionAsignaturaByIdComision(idComision);
    }

    @Override
    public DocenteComisionAsignatura save(DocenteComisionAsignatura entity) throws Exception {
        logger.debug("Ingresa a save()");
        // Primero validamos la hora
        String[] part = entity.getDiaHoraCursado().split("/");
        if (!NRTUtils.validaHora(part[1])) {
            throw new Exception("Hora invalida");
        }
        if (entity.getFuncionProfesor() < 1 || entity.getFuncionProfesor() > 3) {
            throw new Exception("Funcion profesor invalida");
        }

        List<DocenteComisionAsignatura> list = docenteComisionAsignaturaRepository.findDocenteComisionAsignaturaByIdComisionAndIdAsignatura(entity.getComision().getId(),
                                                entity.getAsignatura().getId());

        if (!NRTUtils.isNullOrEmpty(list)) {
            for (DocenteComisionAsignatura l : list) {
                if (l.getFuncionProfesor() == entity.getFuncionProfesor()) {
                    throw new Exception("Ya esta ocupada la funcion de profesor");
                }
            }
            if (list.size() == 3) {
                throw new Exception("Ya estan las 3 posiciones cubiertas para esta comision y para esta asignatura");
            }
        }

        // Seteamos el dia y la hora de cursado formateado
        entity.setDiaHoraCursado(part[0].concat("/").concat(part[1]));
        return docenteComisionAsignaturaRepository.save(entity);
    }

    @Override
    public String getLabelDocenteComisionAsignaturaByComisionAndAsignatura(Long idComision, Long idAsignatura) {
        logger.debug("Ingresa a getDocentesComisionAsignaturasByComisionAndAsignatura()");
        List<DocenteComisionAsignatura> list = new ArrayList<>();
        list = docenteComisionAsignaturaRepository.findDocenteComisionAsignaturaByIdComisionAndIdAsignatura(idComision, idAsignatura);
        String label = "";
        for (DocenteComisionAsignatura dca : list) {
            String profesorFuncion =  "";
            if (dca.getFuncionProfesor() == 1) profesorFuncion = "TEORIA";
            if (dca.getFuncionProfesor() == 2) profesorFuncion = "PRACTICA";
            if (dca.getFuncionProfesor() == 3) profesorFuncion = "AUXILIAR";

            profesorFuncion = profesorFuncion + " " +dca.getDocente().getApellido() + ", " + dca.getDocente().getApellido() + " - ";
            label = label + profesorFuncion + " ";
        }
        return label;
    }
}
