package nrtsig.microservicio.carrera.app.models.dto;

import nrt.microservicios.main.commons.carrera.entity.Carrera;

public class ComisionFiltrosDTO {

	private Integer numeroComision;
	private Carrera carrera;
	
	public ComisionFiltrosDTO() {}

	public Integer getNumeroComision() {
		return numeroComision;
	}

	public void setNumeroComision(Integer numeroComision) {
		this.numeroComision = numeroComision;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
}
