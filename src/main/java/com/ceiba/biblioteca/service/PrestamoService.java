package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.dto.PrestamoDto;
import com.ceiba.biblioteca.entity.Usuario;
import org.springframework.http.ResponseEntity;

public interface PrestamoService {


    Usuario save(Usuario usuario);

    ResponseEntity<?> ConsultarPrestamo(Integer id);

    ResponseEntity<?> findAndSave(PrestamoDto prestamo);
}
