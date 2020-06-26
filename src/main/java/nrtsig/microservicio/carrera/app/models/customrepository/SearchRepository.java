package nrtsig.microservicio.carrera.app.models.customrepository;

import java.util.List;

import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;

public interface SearchRepository {

	public List<Long> searchCarreras(CarreraFiltrosDTO filtrosDTO);
}
