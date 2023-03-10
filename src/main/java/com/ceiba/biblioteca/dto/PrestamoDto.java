package com.ceiba.biblioteca.dto;

public class PrestamoDto {

    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;

    public PrestamoDto() {
    }
    public PrestamoDto(String isbn, String identificaci√≥nUsuario, Integer tipoUsuario) {
        this.isbn = isbn;
        this.identificacionUsuario = identificaci√≥nUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
