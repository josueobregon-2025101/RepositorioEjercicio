package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

@Entity
@Table(name = "Documentos")
public class Documentos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private int idDocumento;

    @NotNull(message = "El cammpo id de Estudiante no puede estar vacio")
    @Positive(message = "El id no puede ser negativo")
    @Column(name = "id_estudiante")
    private int idEstudiante;

    @NotNull(message = "El cammpo id de Empresa no puede estar vacio")
    @Positive(message = "El id no puede ser negativo")
    @Column(name = "id_empresa")
    private int idEmpresa;

    @NotBlank(message = "El tipo de documento no puede estar vacio ")
    @Column(name = "tipo_doc")
    private String tipoDoc;

    @NotBlank(message = "El nombre del archivo no puede estar vacio")
    @Column(name = "nombre_archivo")
    private String nombreArchivo;

    @NotBlank(message = "La url del archivo no puede estar vacio")
    @Column(name = "url_archivo")
    private String urlArchivo;

    @NotBlank(message = "La fecha de subida no puede estar vacia")
    @Column(name = "fecha_subida")
        private String fechaSubida;

    //Getters y Setters

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
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

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    public String getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(String fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}
