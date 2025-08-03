package com.diego.literatura.repository;

import com.diego.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l ORDER BY l.title ASC")
    List<Libro> obtenerTodosLosLibros();

    @Query(value = "SELECT * FROM libros WHERE :idioma = ANY(languages)", nativeQuery = true)
    List<Libro> findByIdioma(@Param("idioma") String idioma);


}
