package nrtsig.microservicio.carrera.app.models.dto;

import nrt.microservicios.main.commons.carrera.entity.Carrera;

import java.util.Date;

public class InscripcionAsignaturaFiltrosDTO {

    private String nombreAlumno;
    private String nombreAsignatura;
    private String estadoAsignatura;    // Descripcion del Entity EstadoAsignatura
    private Carrera carrera;
    private Date fechaInscripcionDesde;
    private Date fechaInscripcionHasta;

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getEstadoAsignatura() {
        return estadoAsignatura;
    }

    public void setEstadoAsignatura(String estadoAsignatura) {
        this.estadoAsignatura = estadoAsignatura;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
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
}
