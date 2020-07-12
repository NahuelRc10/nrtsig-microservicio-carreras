package nrtsig.microservicio.carrera.app.services;

import java.util.List;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.InscripcionCarrera;
import nrtsig.microservicio.carrera.app.models.dto.InscripcionCarreraFiltrosDTO;

public interface InscripcionCarreraService extends CommonService<InscripcionCarrera> {

	public List<InscripcionCarrera> searchInscripcionesCarrera(InscripcionCarreraFiltrosDTO filtrosDTO);
	public InscripcionCarrera confirmarInscripcionCarrera(Long id) throws Exception;
	public InscripcionCarrera reprobarInscripcionCarrera(Long id) throws Exception;
}
