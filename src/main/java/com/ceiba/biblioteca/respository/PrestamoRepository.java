package com.ceiba.biblioteca.respository;

import com.ceiba.biblioteca.entity.Prestamo;
import org.springframework.data.repository.CrudRepository;

public interface PrestamoRepository extends CrudRepository<Prestamo,Integer> {
}
