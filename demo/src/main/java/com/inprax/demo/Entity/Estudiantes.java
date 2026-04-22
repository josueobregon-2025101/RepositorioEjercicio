package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Estudiantes")
public class Estudiantes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer idestudiante;

    @NotNull(message = "La institución es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_institucion")
    private Institucion institucion;

    @Column(name = "id_login")
    private Integer idlogin;

    @NotBlank(message = "El nombre es un campo obligatorio")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El apellido es un campo obligatorio")
    @Column(name = "apellido")
    private String apellido;

    @NotNull(message = "El numero de telefono es un campo obligatorio")
    @Positive(message = "El numero de telefono debe ser positivo")
    @Column(name = "telefono")
    private String telefono;

    @NotBlank(message = "El grado es un campo obligatorio")
    @Column(name = "grado")
    private String grado;

    @NotBlank(message = "La carrera es un campo obligatorio")
    @Column(name = "carrera")
    private String carrera;

    @NotBlank(message = "El correro es un campo obligatorio")
    @Column(name = "correo")
    private String correo;

    @NotBlank(message = "El nombre de la institucion es un campo obligatorio")
    @Column(name = "nombreinstitucion")
    private String nombreInstitucion;

    @NotNull(message = "El numero de telefono del tutor es un campo obligatorio")
    @Positive(message = "El numero de telefono del tutor debe ser positivo")
    @Column(name = "tutortel")
    private String tutortel;

    @NotNull(message = "La edad es un campo obligatorio")
    @Positive(message = "La edad debe ser positiva")
    @Column(name = "edad")
    private Integer edad;

    public Integer getIdestudiante() {
        return idestudiante;
    }

    public void setIdestudiante(Integer idestudiante) {
        this.idestudiante = idestudiante;
    }

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }

    public Integer getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(Integer idlogin) {
        this.idlogin = idlogin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public String getTutortel() {
        return tutortel;
    }

    public void setTutortel(String tutortel) {
        this.tutortel = tutortel;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}