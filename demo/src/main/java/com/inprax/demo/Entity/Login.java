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
    private Integer correoLogin;

    @Column(name = "usuario_login")
    private Integer usuarioLogin;

    @Column(name = "contrasena_login")
    private Integer contrasenaLogin;

    @Column(name = "roles")
    private Integer roles;

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Integer getCorreoLogin() {
        return correoLogin;
    }

    public void setCorreoLogin(Integer correoLogin) {
        this.correoLogin = correoLogin;
    }

    public Integer getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Integer usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Integer getContrasenaLogin() {
        return contrasenaLogin;
    }

    public void setContrasenaLogin(Integer contrasenaLogin) {
        this.contrasenaLogin = contrasenaLogin;
    }

    public Integer getRoles() {
        return roles;
    }

    public void setRoles(Integer roles) {
        this.roles = roles;
    }
}
