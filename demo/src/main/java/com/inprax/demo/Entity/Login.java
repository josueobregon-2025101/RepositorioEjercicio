package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login")
    private Integer idLogin;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo no tiene el formato correcto")
    @Column(name = "correo_login")
    private String correoLogin;

    @NotBlank(message = "El usuario es obligatorio")
    @Column(name = "usuario_login")
    private String usuarioLogin;

    @NotBlank(message = "La contrasena es obligatoria")
    @Column(name = "contrasena_login")
    private String contrasenaLogin;

    @NotBlank(message = "El rol es obligatorio")
    @Column(name = "roles")
    private String roles;

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getCorreoLogin() {
        return correoLogin;
    }

    public void setCorreoLogin(String correoLogin) {
        this.correoLogin = correoLogin;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getContrasenaLogin() {
        return contrasenaLogin;
    }

    public void setContrasenaLogin(String contrasenaLogin) {
        this.contrasenaLogin = contrasenaLogin;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
