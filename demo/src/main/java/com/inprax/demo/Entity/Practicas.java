package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Practicas")
public class Practicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_practica")
    private Integer idPractica;

    @NotNull(message = "El ID de la empresa es obligatorio")
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @NotBlank(message = "El título de la práctica es obligatorio")
    @Column(name = "titulo")
    private String titulo;

    @NotBlank(message = "El tiempo de práctica es obligatorio")
    @Column(name = "tiempo_practica")
    private String tiempoPractica;

    @NotBlank(message = "El tipo de práctica es obligatorio")
    @Column(name = "tipo_practica")
    private String tipoPractica;

    @NotBlank(message = "La carrera requerida es obligatoria")
    @Column(name = "carrera_practica")
    private String carreraPractica;

    @NotBlank(message = "La vigencia de la práctica es obligatoria")
    @Column(name = "vigencia")
    private String vigencia;

    @NotBlank(message = "La disponibilidad es obligatoria")
    @Column(name = "disponibilidad")
    private String disponibilidad;

    @NotBlank(message = "El horario es obligatorio")
    @Column(name = "horario")
    private String horario;

    // Getters y Setters (sin cambios)
    public Integer getIdPractica() {
        return idPractica;
    }

    public void setIdPractica(Integer idPractica) {
        this.idPractica = idPractica;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTiempoPractica() {
        return tiempoPractica;
    }

    public void setTiempoPractica(String tiempoPractica) {
        this.tiempoPractica = tiempoPractica;
    }

    public String getTipoPractica() {
        return tipoPractica;
    }

    public void setTipoPractica(String tipoPractica) {
        this.tipoPractica = tipoPractica;
    }

    public String getCarreraPractica() {
        return carreraPractica;
    }

    public void setCarreraPractica(String carreraPractica) {
        this.carreraPractica = carreraPractica;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}