package com.ceiba.biblioteca.respository;

import com.ceiba.biblioteca.entity.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroRepository extends CrudRepository<Libro,String> {
}
