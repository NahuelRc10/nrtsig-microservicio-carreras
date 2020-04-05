package nrtsig.microservicio.carrera.app.models.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import nrt.microservicios.usuarios.app.models.entity.Alumno;

@Entity
@Table(name = "inscripcion_carrera")
public class InscripcionCarrera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "fecha_inscripcion")
	@NotNull
	private Date fechaInscripcion;
	@Column(name = "nota_promedio")
	private Double notaPromedio;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	@Column(name = "cantidad_asignaturas")
	private Integer cantidadAsignaturasAprobadas;
	@ManyToOne(fetch = FetchType.LAZY)
	private PlanCarrera planCarrera;
	@ManyToOne(fetch = FetchType.LAZY)
	private Alumno alumno;
	@Column(name = "fecha_egreso")
	private Date fechaEgreso;
	@ManyToOne
	@JoinColumn(name = "id_estado")
	private EstadoCarrera estadoCarrera;
	@ManyToOne
	@JoinColumn(name = "id_estado_inscripcion")
	private EstadoInscripcion estadoInscripcion;

	public InscripcionCarrera() {}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
		this.notaPromedio = 0.0;
		this.fechaEgreso = null;
		this.cantidadAsignaturasAprobadas = 0;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Double getNotaPromedio() {
		return notaPromedio;
	}

	public void setNotaPromedio(Double notaPromedio) {
		this.notaPromedio = notaPromedio;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getCantidadAsignaturasAprobadas() {
		return cantidadAsignaturasAprobadas;
	}

	public void setCantidadAsignaturasAprobadas(Integer cantidadAsignaturasAprobadas) {
		this.cantidadAsignaturasAprobadas = cantidadAsignaturasAprobadas;
	}

	public PlanCarrera getPlanCarrera() {
		return planCarrera;
	}

	public void setPlanCarrera(PlanCarrera planCarrera) {
		this.planCarrera = planCarrera;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
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

}
