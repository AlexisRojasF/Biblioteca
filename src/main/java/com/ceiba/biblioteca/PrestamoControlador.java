package com.ceiba.biblioteca;


import com.ceiba.biblioteca.dto.PrestamoDto;
import com.ceiba.biblioteca.entity.Usuario;
import com.ceiba.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    @Autowired
    private PrestamoService prestamoService;
    @PostMapping()
    public ResponseEntity<?> prestamoPost(@RequestBody PrestamoDto prestamo){

        return prestamoService.findAndSave(prestamo);

    }

    @PostMapping("/save")
    public ResponseEntity<?> SaveUser(@RequestBody Usuario usuario){

        return ResponseEntity.ok(prestamoService.save(usuario));

    }

    @GetMapping("/{id-prestamo}")
    public ResponseEntity<?> getPrestamo(@PathVariable(name = "id-prestamo") Integer Id){
        return prestamoService.ConsultarPrestamo(Id);

    }


}

