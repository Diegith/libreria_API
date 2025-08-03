/*package com.diego.literatura.service;

import com.diego.literatura.model.Libro;
import com.diego.literatura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository repository;

    public LibroService(LibroRepository repository) {
        this.repository = repository;
    }

    public Libro save(Libro libro) {
        return repository.save(libro);
    }


    public List<Libro> findAll() {
        return repository.findAll();
    }
}
*/