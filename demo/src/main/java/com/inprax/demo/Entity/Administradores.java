package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Administradores")
public class Administradores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administradores")
    private Integer idAdministradores;

    @NotBlank(message = "El nombre del administrador es obligatorio")
    @Column(name = "nombre_administradores")
    private String nombreAdministradores;

    @NotBlank(message = "El apellido del administrador es obligatorio")
    @Column(name = "apellido_administradores")
    private String apellidoAdministradores;

    @NotBlank(message = "El estado del administrador es obligatorio")
    @Column(name = "estado_administradores")
    private String estadoAdministradores;

    @NotNull(message = "El ID de login es obligatorio")
    @Positive(message = "El ID de login debe ser un numero positivo")
    @Column(name = "id_login")
    private Integer idLogin;

    public Integer getIdAdministradores() {
        return idAdministradores;
    }

    public void setIdAdministradores(Integer idAdministradores) {
        this.idAdministradores = idAdministradores;
    }

    public String getNombreAdministradores() {
        return nombreAdministradores;
    }

    public void setNombreAdministradores(String nombreAdministradores) {
        this.nombreAdministradores = nombreAdministradores;
    }

    public String getApellidoAdministradores() {
        return apellidoAdministradores;
    }

    public void setApellidoAdministradores(String apellidoAdministradores) {
        this.apellidoAdministradores = apellidoAdministradores;
    }

    public String getEstadoAdministradores() {
        return estadoAdministradores;
    }

    public void setEstadoAdministradores(String estadoAdministradores) {
        this.estadoAdministradores = estadoAdministradores;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }
}