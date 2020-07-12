package nrtsig.microservicio.carrera.app.models.dto;

import java.util.Date;

import nrt.microservicios.main.commons.carrera.entity.Departamento;
import nrt.microservicios.main.commons.carrera.entity.EstadoCarrera;
import nrt.microservicios.main.commons.carrera.entity.EstadoInscripcion;

public class InscripcionCarreraFiltrosDTO {

	private String nombreCarrera;
	private EstadoCarrera estadoCarrera;
	private EstadoInscripcion estadoInscripcion;
	private Integer cantAsignaturaAprobDesde;
	private Integer cantAsignaturaAprobHasta;
	private Date fechaInscripcionDesde;
	private Date fechaInscripcionHasta;
	private String nombreAlumno;
	private String apellidoAlumno;
	private Departamento departamento;

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public EstadoCarrera getEstadoCarrera() {
		return estadoCarrera;
	}

	public void setEstadoCarrera(EstadoCarrera estadoCarrera) {
		this.estadoCarrera = estadoCarrera;
	}

	public EstadoInscripcion getEstadoInscripcion() {
		return estadoInscripcion;
	}

	public void setEstadoInscripcion(EstadoInscripcion estadoInscripcion) {
		this.estadoInscripcion = estadoInscripcion;
	}

	public Integer getCantAsignaturaAprobDesde() {
		return cantAsignaturaAprobDesde;
	}

	public void setCantAsignaturaAprobDesde(Integer cantAsignaturaAprobDesde) {
		this.cantAsignaturaAprobDesde = cantAsignaturaAprobDesde;
	}

	public Integer getCantAsignaturaAprobHasta() {
		return cantAsignaturaAprobHasta;
	}

	public void setCantAsignaturaAprobHasta(Integer cantAsignaturaAprobHasta) {
		this.cantAsignaturaAprobHasta = cantAsignaturaAprobHasta;
	}

	public Date getFechaInscripcionDesde() {
		return fechaInscripcionDesde;
	}

	public void setFechaInscripcionDesde(Date fechaInscripcionDesde) {
		this.fechaInscripcionDesde = fechaInscripcionDesde;
	}

	public Date getFechaInscripcionHasta() {
		return fechaInscripcionHasta;
	}

	public void setFechaInscripcionHasta(Date fechaInscripcionHasta) {
		this.fechaInscripcionHasta = fechaInscripcionHasta;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public String getApellidoAlumno() {
		return apellidoAlumno;
	}

	public void setApellidoAlumno(String apellidoAlumno) {
		this.apellidoAlumno = apellidoAlumno;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
