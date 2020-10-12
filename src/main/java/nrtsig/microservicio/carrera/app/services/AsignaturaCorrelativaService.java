package nrtsig.microservicio.carrera.app.services;

import java.util.List;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import nrt.microservicios.main.commons.carrera.entity.AsignaturaCorrelativa;

public interface AsignaturaCorrelativaService extends CommonService<AsignaturaCorrelativa> {

	public List<AsignaturaCorrelativa> getCorrelativasByAsignatura(Long idAsignatura);
	public void borrarCorrelativasSegunAsignatura(Long idAsignatura);
}
