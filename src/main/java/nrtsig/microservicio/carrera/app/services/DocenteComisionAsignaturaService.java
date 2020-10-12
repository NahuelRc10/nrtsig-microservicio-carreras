package nrtsig.microservicio.carrera.app.services;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.DocenteComisionAsignatura;

import java.util.List;

public interface DocenteComisionAsignaturaService extends CommonService<DocenteComisionAsignatura> {

    public List<DocenteComisionAsignatura> getDocenteComisionAsignaturaByIdComision(Long idComision);
    public String getLabelDocenteComisionAsignaturaByComisionAndAsignatura(Long idComision, Long idAsignatura);
}
