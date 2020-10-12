package nrtsig.microservicio.carrera.app.models.dto;

import nrt.microservicios.main.commons.carrera.entity.Carrera;

public class AsignaturaFiltrosDTO {

    private String nombre;
    private Integer nivel;
    private Carrera carrera;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}
