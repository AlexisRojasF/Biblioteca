package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.dto.PrestamoDto;
import com.ceiba.biblioteca.dto.ResPrestamo;
import com.ceiba.biblioteca.dto.StringResponse;
import com.ceiba.biblioteca.entity.Libro;
import com.ceiba.biblioteca.entity.Prestamo;
import com.ceiba.biblioteca.entity.Usuario;
import com.ceiba.biblioteca.respository.LibroRepository;
import com.ceiba.biblioteca.respository.PrestamoRepository;
import com.ceiba.biblioteca.respository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroRepository libroRepository;


    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public ResponseEntity<?> ConsultarPrestamo(Integer id) {

        Optional<Prestamo> prestamo = prestamoRepository.findById(id);

        if (prestamo.isPresent()) {
            return ResponseEntity.ok(prestamo.get());
        } else {
            return ResponseEntity.badRequest().build();
        }

    }


    @Override
    public ResponseEntity<?> findAndSave(PrestamoDto prestamo) {
        Optional<Usuario> usuario = usuarioRepository.findById(prestamo.getIdentificacionUsuario());
        LocalDate date = LocalDate.now();
        StringResponse res = new StringResponse("");
        Optional<Libro> libro = libroRepository.findById(prestamo.getIsbn());

        //Validamos las logitudes de idetificacion usuario y isbn
        if (ValidateErros(prestamo)) {
            return ResponseEntity.badRequest().build();
        }


        if (usuario.isPresent()) {
            //validamos no tenga mas lobros prestadps
            if (validateUser(usuario.get(), prestamo.getTipoUsuario(),date)) {
                res.setMensaje("El usuario con identificación " + usuario.get().getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
                return ResponseEntity.badRequest().body(res);
            }



        //validamos tipo de usuario y calculados dias fechas
        switch (prestamo.getTipoUsuario()) {
            case 1: {
                date = SumarFechas(10);
                break;
            }
            case 2: {
                date = SumarFechas(8);
                break;
            }
            case 3: {
                date = SumarFechas(7);
                break;
            }
            default: {
                res.setMensaje("Tipo de usuario no permitido en la biblioteca");
                return ResponseEntity.badRequest().body(res);
            }
        }


        //guardamos pretamos y creamos respuesta
        Prestamo prestamoSave = new Prestamo();
        prestamoSave.setFechaMaximaDevolucion(date);
        prestamoSave.setIsbn(libro.get().getIsbn());
        prestamoSave.setIdentificacionUsuario(usuario.get().getIdentificacionUsuario());
        prestamoSave.setTipoUsuario(usuario.get().getTipoUsuario());

        usuario.get().setFechaMaximaDevolucion(date);
        usuarioRepository.save(usuario.get());
        prestamoSave = prestamoRepository.save(prestamoSave);

        ResPrestamo resOk = new ResPrestamo();
        resOk.setId(prestamoSave.getId());
        resOk.setFechaMaximaDevolucion(prestamoSave.getFechaMaximaDevolucion());

        return ResponseEntity.ok(resOk);


    } else {
        res.setMensaje("No existe el usuario con el id: " + prestamo.getIdentificacionUsuario());
        return ResponseEntity.badRequest().body(res);

    }

}


    private LocalDate SumarFechas(Integer dias) {

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateNow = LocalDate.now();
        dateNow.format(formato);

        LocalDate result = dateNow;
        int addedDays = 0;
        while (addedDays < dias) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }


    private Boolean ValidateErros(PrestamoDto prestamo) {

        Optional<Libro> libro = libroRepository.findById(prestamo.getIsbn());
        if (!libro.isPresent()) {
            return true;
        }

        if (prestamo.getIdentificacionUsuario().length() > 10) {

            return true;
        }

        if (prestamo.getIsbn().length() > 10) {

            return true;
        }

        return false;

    }

    private  boolean validateUser(Usuario usuario, int i, LocalDate date) {

        if (i != 1) {
            if (usuario.getFechaMaximaDevolucion() != null) {
                if (date.isBefore(usuario.getFechaMaximaDevolucion())) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

}
