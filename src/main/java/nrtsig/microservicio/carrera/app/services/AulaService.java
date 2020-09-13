package nrtsig.microservicio.carrera.app.services;

import nrt.microservicios.commons.services.CommonService;
import nrt.microservicios.main.commons.carrera.entity.Aula;

import java.util.List;

public interface AulaService extends CommonService<Aula> {

    public Integer getNumeroSalon();
    public List<Aula> getAulaByComision(Long idComision);
    public void liberarAulaByIdComision(Long id, Long idComision);
}
