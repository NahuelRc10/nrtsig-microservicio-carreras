package nrtsig.microservicio.carrera.app.services;

import java.util.List;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.Comision;
import nrtsig.microservicio.carrera.app.models.dto.ComisionFiltrosDTO;

public interface ComisionService extends CommonService<Comision> {

	public List<Comision> search(ComisionFiltrosDTO filtrosDTO);
	public boolean existComisionParaCarrera(Long idPlanCarrera, Integer numeroComision);
	public Comision updateComision(Long id, Comision comision) throws Exception;
	public List<Comision> getComisionesByIdCarrera(Long idCarrera);
	public List<Comision> getComisionesByPlanCarreraAndNivelAsignatura(Long idPlanCarrera, Integer nivelAsignatura);
}
