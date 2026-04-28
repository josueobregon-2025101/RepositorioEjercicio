package com.inprax.demo.DTO;

public class EmpresaDTO {
    // LOGIN
    private String usuarioLogin;
    private String correoLogin;
    private String contrasenaLogin;

    // EMPRESA
    private String nombreEmpresa;
    private String tipoEmpresa;
    private String telefonoEmpresa;
    private String correoEmpresa;

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getCorreoLogin() {
        return correoLogin;
    }

    public void setCorreoLogin(String correoLogin) {
        this.correoLogin = correoLogin;
    }

    public String getContrasenaLogin() {
        return contrasenaLogin;
    }

    public void setContrasenaLogin(String contrasenaLogin) {
        this.contrasenaLogin = contrasenaLogin;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public String getCorreoEmpresa() {
        return correoEmpresa;
    }

    public void setCorreoEmpresa(String correoEmpresa) {
        this.correoEmpresa = correoEmpresa;
    }
}
