package nrtsig.microservicio.carrera.app.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nrt.microservicios.commons.services.CommonServiceImpl;
import nrt.microservicios.main.commons.carrera.entity.Aula;
import nrtsig.microservicio.carrera.app.models.repository.AulaRepository;
import nrtsig.microservicio.carrera.app.services.AulaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AulaServiceImpl extends CommonServiceImpl<Aula, AulaRepository> implements AulaService {

    private static final Logger logger = LoggerFactory.getLogger(AulaServiceImpl.class);
    @Autowired
    private AulaRepository aulaRepository;

    @Override
    public Integer getNumeroSalon() {
        logger.debug("Ingresa a getNumeroSalon()");
        Integer numeroSalon = null;
        List<Aula> aulaList = (List<Aula>) aulaRepository.findAll();
        for (int i = 1; i < aulaList.size(); i++) {
            if (aulaList.get(i).getComision() == null) {
                numeroSalon = i + 1;
                break;
            }
        }
        if (numeroSalon == null) {
            numeroSalon = aulaList.size() + 1;
        }
        return numeroSalon;
    }

    @Override
    public List<Aula> getAulaByComision(Long idComision) {
        logger.debug("Ingresa a getAulaByComision()");
        List<Aula> aulas = new ArrayList<>();
        aulas = aulaRepository.findAulaByIdComision(idComision);
        return aulas;
    }

    @Override
    public void liberarAulaByIdComision(Long id, Long idComision) {
        logger.debug("Ingresa a liberarAulaByIdComision()");
        // Validamos si el id del aula es distinto de null
        if (id != null) {
            // Implica que debemos liberar el aula en base al id del aula
            aulaRepository.updateAulaById(id);
        } else {
            // Implica que debemos liberar el aula en base al id de la comision
            aulaRepository.updateIdComision(idComision);
        }
    }

    @Override
    public Aula save(Aula entity) throws Exception {
        logger.debug("Ingresa a save()");
        // Validamos que el numero de aula no exista
        Aula aula = aulaRepository.findAulaByNumeroSalon(entity.getNumeroSalon());
        if (aula == null) {
            // Significa que el no existe una aula con ese numero
            return aulaRepository.save(entity);
        } else {
            // Existe un aula con dicho numero por lo tanto setear la comision y actualizar
            aula.setComision(entity.getComision());
            return aulaRepository.save(aula);
        }
    }
}
