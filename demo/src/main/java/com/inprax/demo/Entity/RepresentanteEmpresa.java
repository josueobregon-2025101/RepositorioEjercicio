package com.inprax.demo.Entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "RepresentanteEmpresa")
public class RepresentanteEmpresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_representantempresa")
    private Integer idrepresentantempresa;

    @Column(name = "id_empresa")
    private Integer idempresa;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "extension")
    private String extension;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_registro")
    private Date fecharegistro;

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
