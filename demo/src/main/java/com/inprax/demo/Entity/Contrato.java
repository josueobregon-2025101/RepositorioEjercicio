package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private int idContrato;

    @NotNull(message = "El id de postulacion no debe estar vacio")
    @Positive(message = "El id postulacion no puede ser negativo")
    @Column(name = "id_postulacion ")
    private int idPostulacion;

    @NotNull(message = "El id de empresa no debe estar vacio")
    @Positive(message = "El id empresa no puede ser negativo")
    @Column(name = "id_empresa  ")
    private int idEmpresa;

    @NotNull(message = "El id de estudiante no debe estar vacio")
    @Positive(message = "El id estudiante no puede ser negativo")
    @Column(name = "id_estudiante   ")
    private int idEstudiante;

    @NotNull(message = "El id de documento  no debe estar vacio")
    @Positive(message = "El id documento no puede ser negativo")
    @Column(name = "id_documento")
    private int idDocumento;

    @NotBlank(message = "La fecha de inicio no puede estar vacia")
    @Column(name = "fechaInicio")
    private String fechaInicio;

    @NotBlank(message = "La fecha final no puede estar vacia")
    @Column(name = "fechaFin")
    private String fechaFin;

    @NotBlank(message = "Los objetivos no pueden estar vacios")
    @Column(name = "objetivos")
    private String objetivos;

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(int idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }
}
