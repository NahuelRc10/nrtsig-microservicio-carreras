package nrtsig.microservicio.carrera.app.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nrt.microservicios.main.commons.carrera.entity.Asignatura;
import nrt.microservicios.main.commons.carrera.entity.Comision;
import nrt.microservicios.main.commons.carrera.entity.PlanCarrera;
import nrt.microservicios.main.commons.usuario.entity.Alumno;

import java.util.List;


public class InscripcionAsignaturaGroupDTO {

    @JsonIgnoreProperties(value = {"asignaturas", "carreras", "hibernateLazyInitializer", "handler"})
    private List<Alumno> alumnos;
    @JsonIgnoreProperties(value = {"alumnosInscriptos", "hibernateLazyInitializer", "handler"})
    private PlanCarrera planCarrera;
    @JsonIgnoreProperties(value = {"planCarrera", "hibernateLazyInitializer", "handler"})
    private Comision comision;
    @JsonIgnoreProperties(value = {"alumnosInscriptos", "asignaturasHijas", "hibernateLazyInitializer", "handler"})
    private Asignatura asignatura;

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public PlanCarrera getPlanCarrera() {
        return planCarrera;
    }

    public void setPlanCarrera(PlanCarrera planCarrera) {
        this.planCarrera = planCarrera;
    }

    public Comision getComision() {
        return comision;
    }

    public void setComision(Comision comision) {
        this.comision = comision;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}
