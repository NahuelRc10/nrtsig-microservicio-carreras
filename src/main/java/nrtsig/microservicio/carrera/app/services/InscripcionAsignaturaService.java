package nrtsig.microservicio.carrera.app.services;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.InscripcionAsignatura;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionAsignaturaFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionAsignaturaGroupDTO;

import java.util.List;

public interface InscripcionAsignaturaService extends CommonService<InscripcionAsignatura> {

    public List<InscripcionAsignatura> searchInscripciones(InscripcionAsignaturaFiltrosDTO filtrosDTO);
    public Integer registrarInscripciones(InscripcionAsignaturaGroupDTO dto) throws Exception;

}
