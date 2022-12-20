package com.ceiba.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Usuario {

    @Id
    @Size(max = 10,message = "No debe Superar 10 carateres")
    private String identificacionUsuario;

    private Integer tipoUsuario;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT+8")
    private LocalDate fechaMaximaDevolucion;


    public Usuario(String identificacionUsuario, Integer tipoUsuario, LocalDate fechaMaximaDevolucion) {
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public Usuario() {
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificaciónUsuario) {
        this.identificacionUsuario = identificaciónUsuario;
    }

    public Integer getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Integer tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDate getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(LocalDate fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }
}
