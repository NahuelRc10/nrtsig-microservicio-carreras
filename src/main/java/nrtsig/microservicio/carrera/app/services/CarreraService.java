package nrtsig.microservicio.carrera.app.services;


import java.util.List;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.Carrera;
import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;

public interface CarreraService extends CommonService<Carrera> {

	public List<Carrera> search(CarreraFiltrosDTO filtrosDTO);
}
