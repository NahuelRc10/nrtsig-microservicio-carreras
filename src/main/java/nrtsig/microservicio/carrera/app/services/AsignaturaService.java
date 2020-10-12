package nrtsig.microservicio.carrera.app.services;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import nrtsig.microservicio.carrera.app.models.dto.AsignaturaFiltrosDTO;

import java.util.List;

public interface AsignaturaService extends CommonService<Asignatura> {

    public List<Asignatura> searchAsignaturasByFiltros(AsignaturaFiltrosDTO filtrosDTO);
    public List<Asignatura> getAsignaturasPosiblesCorrelatividades(Integer nivel, Long idPlanCarrera, Integer tipoAsignatura);
    public void actualizarAsignatura(Long id, String descripcion);
    public List<Asignatura> getAsignaturaByCarrera(Long idCarrera, Long idComision, Integer nroComision);
    public List<Asignatura> getAsignaturasPosiblesInscripcionSegunAlumno(Long idAlumno, Integer nivel, Long idPlanCarrera);
    public List<Asignatura> getAsignaturasSegunNivelAndPlanCarrera(Integer nivel, Long idPlanCarrera);
}
