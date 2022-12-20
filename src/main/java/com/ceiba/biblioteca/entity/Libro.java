package com.ceiba.biblioteca.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Libro {

    @Id
    @Size(max = 10,message = "No debe Superar 10 carateres")
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
