package nrtsig.microservicio.carrera.app.services.impl;

import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Departamento;
import nrtsig.microservicio.carrera.app.models.repository.DepartamentoRepository;
import nrtsig.microservicio.carrera.app.services.DepartamentoService;

@Service
public class DepartamentoServiceImpl extends CommonServiceImpl<Departamento, DepartamentoRepository> implements DepartamentoService {

}
