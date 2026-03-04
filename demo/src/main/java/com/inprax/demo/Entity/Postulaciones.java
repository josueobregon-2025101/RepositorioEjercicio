package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Postulaciones")
public class Postulaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postulacion")
    private Integer idPostulacion;

    @NotNull(message = "El ID de la práctica es obligatorio")
    @Column(name = "id_practica")
    private Integer idPractica;

    @NotBlank(message = "El título de la postulación es obligatorio")
    @Column(name = "titulo")
    private String titulo;

    @NotBlank(message = "La descripción de la postulación es obligatoria")
    @Column(name = "descripcion")
    private String descripcion;

    @NotBlank(message = "La fecha de postulación es obligatoria")
    @Column(name = "fecha_post")
    private String fechaPost;

    @NotBlank(message = "El estado de la postulación es obligatorio")
    @Column(name = "estado")
    private String estado;

    // Getters y Setters (sin cambios)
    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Integer getIdPractica() {
        return idPractica;
    }

    public void setIdPractica(Integer idPractica) {
        this.idPractica = idPractica;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaPost() {
        return fechaPost;
    }

    public void setFechaPost(String fechaPost) {
        this.fechaPost = fechaPost;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}