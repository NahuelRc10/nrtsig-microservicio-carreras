package nrtsig.microservicio.carrera.app.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Carrera;
import nrtsig.microservicio.carrera.app.models.customrepository.SearchRepository;
import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.repository.CarreraRepository;
import nrtsig.microservicio.carrera.app.services.CarreraService;

@Service
public class CarreraServiceImpl extends CommonServiceImpl<Carrera, CarreraRepository> implements CarreraService {

	@Autowired
	private CarreraRepository carreraRepository;
	@Autowired
	private SearchRepository searchRepository;

	@Override
	public List<Carrera> search(CarreraFiltrosDTO filtrosDTO) {
		// Primero recupero los ids de las carreras de acuerdo a los filtros seleccionados
		List<Long> idList = searchRepository.searchCarreras(filtrosDTO);
		// Luego recuperamos del repository las carreras correspondientes
		List<Carrera> carreras = new ArrayList<Carrera>();
		return (List<Carrera>) carreraRepository.findAllById(idList);
	}
}
