package nrtsig.microservicio.carrera.app.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Carrera;
import nrt.microservicios.main.commons.carrera.entity.TipoCarrera;
import nrtsig.microservicio.carrera.app.models.customrepository.SearchRepository;
import nrtsig.microservicio.carrera.app.models.dto.CarreraFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.repository.CarreraRepository;
import nrtsig.microservicio.carrera.app.services.CarreraService;
import utils.NRTUtils;

@Service
public class CarreraServiceImpl extends CommonServiceImpl<Carrera, CarreraRepository> implements CarreraService {

	@Autowired
	private CarreraRepository carreraRepository;
	@Autowired
	private SearchRepository searchRepository; 
	
	@Override
	public Carrera save(Carrera entity) throws Exception {
		// Validamos que el nombre de la carrera no contenga numeros
		if (NRTUtils.cadContainsDigit(entity.getNombre())) {
			throw new Exception("El nombre de la carrera no puede contener numeros, solo letras");
		}
		// Validamos la duracion de la carrera de acuerdo al tipo de carrera
		TipoCarrera tipoCarrera = entity.getTipoCarrera();
		if (!(entity.getDuracion() >= tipoCarrera.getCantidadMinAnios() && entity.getDuracion() <= tipoCarrera.getCantidadMaxAnios())) {
			throw new Exception("La duracion no es valida segun el tipo de carrera elegida");
		}
		
		// Instanciamos el objeto que vamos a persistir en la BBDD
		Carrera carreraDb = new Carrera();
		carreraDb.setNombre(entity.getNombre());
		carreraDb.setNombreCorto(NRTUtils.cadToUpperCase(entity.getNombreCorto()));
		carreraDb.setDuracion(entity.getDuracion());
		carreraDb.setDescripcion(entity.getDescripcion());
		carreraDb.setDepartamento(entity.getDepartamento());
		carreraDb.setTipoCarrera(entity.getTipoCarrera());
		carreraDb.setCarreraActiva(false);
		return carreraRepository.save(carreraDb);
	}

	@Override
	public List<Carrera> search(CarreraFiltrosDTO filtrosDTO) {
		// Primero recupero los ids de las carreras de acuerdo a los filtros seleccionados
		List<Long> idList = searchRepository.searchCarreras(filtrosDTO);
		// Luego recuperamos del repository las carreras correspondientes
		List<Carrera> carreras = new ArrayList<Carrera>();
		carreras = (List<Carrera>) carreraRepository.findAllById(idList);
		return carreras;
	}
}
