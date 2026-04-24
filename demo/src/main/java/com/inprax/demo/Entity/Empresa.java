package com.inprax.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;


    @Column(name = "tipo_empresa")
    private String tipoEmpresa;


    @Column(name = "tamano_empresa")
    private String tamanoEmpresa;


    @Column(name = "telefono_empresa")
    private String telefonoEmpresa;


    @Column(name = "correo_empresa")
    private String correoEmpresa;


    @Column(name = "direccion_empresa")
    private String direccionEmpresa;

    @Column(name = "horario_empresa")
    private String horarioEmpresa;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    // VERIFICAR PORQUE APARECE LOGIN SI SE DEBE CREAR ANTES UNA EMPRESA @NotNull(message = "El ID de login es obligatorio")
    @Positive(message = "El ID de login debe ser un numero positivo")
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
