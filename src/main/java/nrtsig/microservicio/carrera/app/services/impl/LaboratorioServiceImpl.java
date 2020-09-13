package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Laboratorio;
import nrtsig.microservicio.carrera.app.models.repository.LaboratorioRepository;
import nrtsig.microservicio.carrera.app.services.LaboratorioService;

@Service
public class LaboratorioServiceImpl extends CommonServiceImpl<Laboratorio, LaboratorioRepository> implements LaboratorioService {

}
