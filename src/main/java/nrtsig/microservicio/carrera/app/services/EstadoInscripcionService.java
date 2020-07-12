package nrtsig.microservicio.carrera.app.services;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;

public interface EstadoInscripcionService extends CommonService<EstadoInscripcion> {

	public EstadoInscripcion getEstadoInscripcionSegunCodigo(String codigo);
}
