package nrtsig.microservicio.carrera.app.models.dto;

import nrt.microservicios.main.commons.carrera.entity.Departamento;
import nrt.microservicios.main.commons.carrera.entity.TipoCarrera;

public class CarreraFiltrosDTO {

	private String nombre;
	private Integer duracion;
	private Boolean estadoCarrera;
	private TipoCarrera tipoCarrera;
	private Departamento departamento;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Boolean getEstadoCarrera() {
		return estadoCarrera;
	}

	public void setEstadoCarrera(Boolean estadoCarrera) {
		this.estadoCarrera = estadoCarrera;
	}

	public TipoCarrera getTipoCarrera() {
		return tipoCarrera;
	}

	public void setTipoCarrera(TipoCarrera tipoCarrera) {
		this.tipoCarrera = tipoCarrera;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
}
