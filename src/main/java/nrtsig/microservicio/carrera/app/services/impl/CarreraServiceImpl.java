package nrtsig.microservicio.carrera.app.services.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(CarreraServiceImpl.class);
	@Autowired
	private CarreraRepository carreraRepository;
	@Autowired
	private SearchRepository searchRepository; 
	
	@Override
	public Carrera save(Carrera entity) throws Exception {
		logger.debug("Ingresa a save()");
		// Validamos que la carrera no exista
		Long idCarrera = carreraRepository.findIdCarreraByNombreAndNombreCorto(entity.getNombre(), entity.getNombreCorto());
		if (idCarrera != null) {
			throw new Exception("La carrera ya existe");
		}
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
		logger.debug("Ingresa a search()");
		// Primero recupero los ids de las carreras de acuerdo a los filtros seleccionados
		List<Long> idList = searchRepository.searchCarreras(filtrosDTO);
		// Luego recuperamos del repository las carreras correspondientes
		List<Carrera> carreras = new ArrayList<Carrera>();
		carreras = (List<Carrera>) carreraRepository.findAllById(idList);
		return carreras;
	}

	@Override
	public void activarCarrera(Long id) throws Exception {
		logger.debug("Ingresa el activarCarrera()");
		Optional<Carrera> carreraOpt = carreraRepository.findById(id);
		if (carreraOpt.isEmpty()) {
			throw new Exception("La carrera no existe en la base de datos");
		}
		Carrera carreraDb = carreraOpt.get();
		carreraDb.setCarreraActiva(true);
		carreraRepository.save(carreraDb);
	}

	@Override
	public Carrera actualizarCarrera(Carrera carrera, Long id) throws Exception {
		logger.debug("Ingresa el actualizarCarrera()");
		Optional<Carrera> o = carreraRepository.findById(id);
		if (o.isEmpty()) {
			throw new Exception("No existe la carrera");
		}
		Carrera carreraDb = o.get();
		carreraDb.setId(carrera.getId());
		carreraDb.setNombre(carrera.getNombre());
		carreraDb.setNombreCorto(carrera.getNombreCorto());
		carreraDb.setDescripcion(carrera.getDescripcion());
		carreraDb.setDuracion(carrera.getDuracion());
		carreraDb.setDepartamento(carrera.getDepartamento());
		carreraDb.setTipoCarrera(carrera.getTipoCarrera());
		carreraDb.setPlanesCarrera(carrera.getPlanesCarrera());
		carreraDb.setCarreraActiva(carrera.getCarreraActiva());
		return carreraRepository.save(carreraDb);
	}

	@Override
	public void desactivarCarrera(Long id) {
		logger.debug("Ingresa a desactivarCarrera()");
		carreraRepository.updateCarreraActiva(id);
	}

	@Override
	public List<Carrera> getCarrerasOrdenadasByNombre() {
		logger.debug("Ingresa a getCarrerasOrdenadasByNombre()");
		List<Carrera> carrerasOrdenadas = carreraRepository.findCarrerasOrderByNombre();
		return carrerasOrdenadas;
	}

	@Override
	public List<Carrera> getCarreraByDepartamento(Long idDepartamento) {
		logger.debug("Ingresa a getCarreraByDepartamento()");
		List<Carrera> carreras = new ArrayList<Carrera>();
		carreras = carreraRepository.findCarreraByDepartamentoOrderByNombre(idDepartamento);
		return carreras;
	}

	@Override
	public List<Carrera> getCarrerasByTipoCarrera(Long idTipoCarrera) {
		logger.debug("Ingresa a getCarrerasByTipoCarrera()");
		List<Carrera> carreras = new ArrayList<Carrera>();
		carreras = carreraRepository.findCarreraByTipoCarrera(idTipoCarrera);
		return carreras;
	}
}
