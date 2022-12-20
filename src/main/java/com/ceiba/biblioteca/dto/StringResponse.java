package com.ceiba.biblioteca.dto;

public class StringResponse {

    private String mensaje;

    public StringResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
