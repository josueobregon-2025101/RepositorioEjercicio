package com.inprax.demo.DTO;

public class EstudianteDTO {

    private String usuarioLogin;
    private String correoLogin;
    private String contrasenaLogin;

    private String nombre;
    private String apellido;
    private String telefono;
    private String grado;
    private String carrera;
    private String correo;
    private String nombreInstitucion;
    private String tutortel;
    private Integer edad;
    private Integer idInstitucion;

    public String getUsuarioLogin() { return usuarioLogin; }
    public void setUsuarioLogin(String usuarioLogin) { this.usuarioLogin = usuarioLogin; }

    public String getCorreoLogin() { return correoLogin; }
    public void setCorreoLogin(String correoLogin) { this.correoLogin = correoLogin; }

    public String getContrasenaLogin() { return contrasenaLogin; }
    public void setContrasenaLogin(String contrasenaLogin) { this.contrasenaLogin = contrasenaLogin; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getNombreInstitucion() { return nombreInstitucion; }
    public void setNombreInstitucion(String nombreInstitucion) { this.nombreInstitucion = nombreInstitucion; }

    public String getTutortel() { return tutortel; }
    public void setTutortel(String tutortel) { this.tutortel = tutortel; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public Integer getIdInstitucion() { return idInstitucion; }
    public void setIdInstitucion(Integer idInstitucion) { this.idInstitucion = idInstitucion; }
}

