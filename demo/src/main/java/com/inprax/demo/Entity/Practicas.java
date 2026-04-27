package com.inprax.demo.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Practicas")
public class Practicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_practica")
    private Integer idPractica;

    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "tiempo_practica")
    private LocalDateTime tiempoPractica;

    @Column(name = "tipo_practica")
    private String tipoPractica;

    @Column(name = "carrera_practica")
    private String carreraPractica;

    @Column(name = "vigencia")
    private String vigencia;

    @Column(name = "disponibilidad")
    private String disponibilidad;

    @Column(name = "horario")
    private LocalDateTime horario;

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

    public LocalDateTime getTiempoPractica() {
        return tiempoPractica;
    }

    public void setTiempoPractica(LocalDateTime tiempoPractica) {
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

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}