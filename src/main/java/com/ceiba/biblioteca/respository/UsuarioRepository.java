package com.ceiba.biblioteca.respository;

import com.ceiba.biblioteca.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,String> {
}
