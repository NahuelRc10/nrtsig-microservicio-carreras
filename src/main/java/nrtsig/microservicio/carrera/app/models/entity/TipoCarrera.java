package nrtsig.microservicio.carrera.app.models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tipo_carreras")
public class TipoCarrera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "tipo_carrera")
	@NotEmpty
	private String tipoCarrera;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	@OneToMany(mappedBy = "tipoCarrera")
	private List<Carrera> carreras;
	
	public TipoCarrera() {}
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipoCarrera() {
		return tipoCarrera;
	}
	
	public void setTipoCarrera(String tipoCarrera) {
		this.tipoCarrera = tipoCarrera;
	}
	
	public Date getCreateAt() {
		return this.createAt;
	} 
	
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public List<Carrera> getCarreras() {
		return carreras;
	}
	
	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}
	
}
