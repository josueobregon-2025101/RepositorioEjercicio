package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.sql.Date;

@Entity
@Table(name = "RepresentanteEmpresa")
public class RepresentanteEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_representantempresa")
    private Integer idrepresentantempresa;

    @NotNull(message = "El id de la empresa es un campo obligatorio")
    @Positive(message = "El id de la empresa debe ser positivo")
    @Column(name = "id_empresa")
    private Integer idempresa;

    @NotBlank(message = "El nombre es un campo obligatorio")
    @Column(name = "nombres")
    private String nombres;

    @NotBlank(message = "El apellido es un campo obligatorio")
    @Column(name = "apellidos")
    private String apellidos;

    @NotBlank(message = "El cargo es un campo obligatorio")
    @Column(name = "cargo")
    private String cargo;

    @NotNull(message = "El numero de telefono es un campo obligatorio")
    @Positive(message = "El numero de telefono debe ser positivo")
    @Column(name = "telefono")
    private Integer telefono;

    @NotBlank(message = "La extension es un campo obligatorio")
    @Column(name = "extension")
    private String extension;

    @NotBlank(message = "El estado es un campo obligatorio")
    @Column(name = "estado")
    private String estado;

    @NotNull(message = "La fecha del registro es un campo obligatorio")
    @Positive(message = "La fecha del registro debe ser positiva")
    @Column(name = "fecha_registro")
    private Date fecharegistro;

    @NotBlank(message = "El correro es un campo obligatorio")
    @Column(name = "correo")
    private String correo;

    //Getters and Setters//

    public Integer getIdrepresentantempresa() {
        return idrepresentantempresa;
    }

    public void setIdrepresentantempresa(Integer idrepresentantempresa) {
        this.idrepresentantempresa = idrepresentantempresa;
    }

    public Integer getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Integer idempresa) {
        this.idempresa = idempresa;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
