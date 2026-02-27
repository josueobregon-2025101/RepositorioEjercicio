package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Administradores")
public class Administradores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administradores")
    private Integer idAdministradores;

    @NotBlank(message = "El nombre del administrador es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(name = "nombre_administradores")
    private String nombreAdministradores;

    @NotBlank(message = "El apellido del administrador es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Column(name = "apellido_administradores")
    private String apellidoAdministradores;

    @NotBlank(message = "El estado del administrador es obligatorio")
    @Pattern(regexp = "^(activo|inactivo)$", message = "El estado debe ser 'activo' o 'inactivo'")
    @Column(name = "estado_administradores")
    private String estadoAdministradores;

    @NotNull(message = "El ID de login es obligatorio")
    @Positive(message = "El ID de login debe ser un número positivo")
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