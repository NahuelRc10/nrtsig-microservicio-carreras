package nrtsig.microservicio.carrera.app.models.dto;

import nrt.microservicios.main.commons.carrera.entity.Carrera;
import nrt.microservicios.main.commons.carrera.entity.Departamento;
import nrt.microservicios.main.commons.carrera.entity.TipoCarrera;

public class PlanCarreraFiltrosDTO {

	private Integer anioPlanDesde;
	private Integer anioPlanHasta;
	private Carrera carrera;
	private TipoCarrera tipoCarrera;
	private Departamento dpto;

	public Integer getAnioPlanDesde() {
		return anioPlanDesde;
	}

	public void setAnioPlanDesde(Integer anioPlanDesde) {
		this.anioPlanDesde = anioPlanDesde;
	}

	public Integer getAnioPlanHasta() {
		return anioPlanHasta;
	}

	public void setAnioPlanHasta(Integer anioPlanHasta) {
		this.anioPlanHasta = anioPlanHasta;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public TipoCarrera getTipoCarrera() {
		return tipoCarrera;
	}

	public void setTipoCarrera(TipoCarrera tipoCarrera) {
		this.tipoCarrera = tipoCarrera;
	}

	public Departamento getDpto() {
		return dpto;
	}

	public void setDpto(Departamento dpto) {
		this.dpto = dpto;
	}
}
