package com.inprax.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Administradores")
public class Administradores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administradores")
    private Integer idAdministradores;

    @Column(name = "nombre_administradores")
    private String nombreAdministradores;

    @Column(name = "apellido_administradores")
    private String apellidoAdministradores;

    @Column(name = "estado_administradores")
    private String estadoAdministradores;

    @Column(name = "id_login")
    private Integer idLogin;

    public Integer getIdAdministradores() { return idAdministradores; }
    public void setIdAdministradores(Integer idAdministradores) { this.idAdministradores = idAdministradores; }

    public String getNombreAdministradores() { return nombreAdministradores; }
    public void setNombreAdministradores(String nombreAdministradores) { this.nombreAdministradores = nombreAdministradores; }

    public String getApellidoAdministradores() { return apellidoAdministradores; }
    public void setApellidoAdministradores(String apellidoAdministradores) { this.apellidoAdministradores = apellidoAdministradores; }

    public String getEstadoAdministradores() { return estadoAdministradores; }
    public void setEstadoAdministradores(String estadoAdministradores) { this.estadoAdministradores = estadoAdministradores; }

    public Integer getIdLogin() { return idLogin; }
    public void setIdLogin(Integer idLogin) { this.idLogin = idLogin; }
}