package nrtsig.microservicio.carrera.app.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nrtsig.microservicio.carrera.app.services.AulaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Comision;
import nrtsig.microservicio.carrera.app.models.customrepository.SearchRepository;
import nrtsig.microservicio.carrera.app.models.dto.ComisionFiltrosDTO;
import nrtsig.microservicio.carrera.app.models.repository.ComisionRepository;
import nrtsig.microservicio.carrera.app.services.ComisionService;

@Service
public class ComisionServiceImpl extends CommonServiceImpl<Comision, ComisionRepository> implements ComisionService {

	private final static Logger logger = LoggerFactory.getLogger(ComisionServiceImpl.class);
	@Autowired
	private ComisionRepository comisionRepository;
	@Autowired
	private SearchRepository searchRepository;
	@Autowired
	private AulaService aulaService;
	
	@Override
	public List<Comision> search(ComisionFiltrosDTO filtrosDTO) {
		logger.debug("Ingresa a search()");
		List<Long> idList = new ArrayList<Long>();
		idList = searchRepository.searchComision(filtrosDTO);
		List<Comision> comisiones = new ArrayList<Comision>();
		comisiones = comisionRepository.findAllById(idList);
		return comisiones;
	}

	@Override
	public boolean existComisionParaCarrera(Long idPlanCarrera, Integer numeroComision) {
		logger.debug("Ingresa a existComisionParaCarrera()");
		Long id = comisionRepository.findIdComisionByNumeroComisionAndIdPlanCarrera(numeroComision, idPlanCarrera);
		if (id != null) {
			return true;
		}
		return false;
	}

	@Override
	public Comision updateComision(Long id, Comision comision) throws Exception {
		logger.debug("Ingresa a updateComision()");
		Optional<Comision> opt = comisionRepository.findById(id);
		if (opt.get() == null) {
			throw new Exception("No existe la comision que desea actualizar!");
		}
		Comision comisionDb = opt.get();
		comisionDb.setCapacidadMaxima(comision.getCapacidadMaxima());
		return comisionRepository.save(comisionDb);
	}

	@Override
	public Comision save(Comision entity) throws Exception {
		logger.debug("Ingresa a save()");
		Comision comision = new Comision();
		comision.setNumeroComision(entity.getNumeroComision());
		comision.setCapacidadMaxima(entity.getCapacidadMaxima());
		comision.setPlanCarrera(entity.getPlanCarrera());
		comision.setCapacidadActual(0);
		return comisionRepository.save(comision);
	}

	@Override
	public void deleteById(Long id) {
		logger.debug("Ingresa a deleteById()");
		// Primero liberamos el aula para que quede disponible para una futura comision
		aulaService.liberarAulaByIdComision(null, id);
		comisionRepository.deleteById(id);
	}
}
