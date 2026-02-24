package com.inprax.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name ="Login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_login")
    private Integer idLogin;

    @Column(name = "correo_login")
    private String correoLogin;

    @Column(name = "usuario_login")
    private String usuarioLogin;

    @Column(name = "contrasena_login")
    private String contrasenaLogin;

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
