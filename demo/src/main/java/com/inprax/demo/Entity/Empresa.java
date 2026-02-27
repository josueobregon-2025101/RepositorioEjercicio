package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;

    @NotBlank(message = "El tipo de empresa es obligatorio")
    @Size(max = 50, message = "El tipo no puede superar los 50 caracteres")
    @Column(name = "tipo_empresa")
    private String tipoEmpresa;

    @NotBlank(message = "El tamaño de la empresa es obligatorio")
    @Size(max = 30, message = "El tamaño no puede superar los 30 caracteres")
    @Column(name = "tamano_empresa")
    private String tamanoEmpresa;

    @NotBlank(message = "El teléfono de la empresa es obligatorio")
    @Pattern(regexp = "^[+]?[0-9]{7,15}$", message = "El teléfono debe tener entre 7 y 15 dígitos")
    @Column(name = "telefono_empresa")
    private String telefonoEmpresa;

    @NotBlank(message = "El correo de la empresa es obligatorio")
    @Email(message = "El correo electrónico no tiene un formato válido")
    @Column(name = "correo_empresa")
    private String correoEmpresa;

    @NotBlank(message = "La dirección de la empresa es obligatoria")
    @Size(max = 150, message = "La dirección no puede superar los 150 caracteres")
    @Column(name = "direccion_empresa")
    private String direccionEmpresa;

    @Size(max = 100, message = "El horario no puede superar los 100 caracteres")
    @Column(name = "horario_empresa")
    private String horarioEmpresa;

    @Size(max = 1000, message = "La descripción no puede superar los 1000 caracteres")
    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El ID de login es obligatorio")
    @Positive(message = "El ID de login debe ser un número positivo")
    @Column(name = "id_login")
    private Integer idLogin;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public String getTamanoEmpresa() {
        return tamanoEmpresa;
    }

    public void setTamanoEmpresa(String tamanoEmpresa) {
        this.tamanoEmpresa = tamanoEmpresa;
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

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getHorarioEmpresa() {
        return horarioEmpresa;
    }

    public void setHorarioEmpresa(String horarioEmpresa) {
        this.horarioEmpresa = horarioEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }
}
