package com.diego.literatura.principal;

import com.diego.literatura.model.Author;
import com.diego.literatura.model.GutendexResponse;
import com.diego.literatura.model.Libro;
import com.diego.literatura.repository.AutorRepository;
import com.diego.literatura.repository.LibroRepository;
import com.diego.literatura.service.ConsumoAPI;
import com.diego.literatura.service.ConvertirDatos;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    Scanner teclado = new Scanner(System.in);

    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;

    List<Libro> libros;
    List<Author> autores;
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.autorRepositorio = autorRepository;
        this.libroRepositorio = libroRepository;
    }
    public void mostraMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n---------------------------
                    Seleccione una opción (1-5):
                    ---------------------------
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Buscar autor por año de nacimiento
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    Libro libro = buscarLibroPorTitulo();
                    if (libro != null && libro.getAuthors() != null) {
                        List<Author> persistedAuthors = guardarAutor(libro.getAuthors());
                        libro.setAuthors(persistedAuthors);
                        libroRepositorio.save(libro);
                        System.out.println("Libro registrado exitosamente:");
                    }
                    break;
                case 2:
                    mostarTodosLosLibros();
                    break;
                case 3:
                    mostarTodosLosAutores();
                    break;
                case 4:
                    buscarAutorPorAño();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
            }
        }
    }

    private List<Libro> getLibros(){
        String API_URL = "https://gutendex.com/books";
        var json = consumoApi.obtenerDatos(API_URL);
        ConvertirDatos convertirDatos = new ConvertirDatos();
        GutendexResponse gutendexResponse = convertirDatos.obtenerDatos(json, GutendexResponse.class);
        return gutendexResponse.getResults();
    }

    private List<Author> guardarAutor(List<Author> autores) {
        for (int i = 0; i < autores.size(); i++) {
            Author autor = autores.get(i);
            Author persisted = autorRepositorio.findByName(autor.getName())
                    .orElseGet(() -> autorRepositorio.save(autor));
            autores.set(i, persisted);
        }
        return autores;
    }

    private Libro buscarLibroPorTitulo() {
        System.out.println("Ingrese el título del libro:");
        String titulo = teclado.nextLine().toLowerCase();
        List<Libro> libros = getLibros();
        for (Libro libro : libros) {
            if (libro.getTitle().toLowerCase().contains(titulo)) {
                return libro;
            }
        }
        System.out.println("Libro no encontrado.");
        return null;
    }

    private void mostarTodosLosLibros() {
        libros = libroRepositorio.obtenerTodosLosLibros();
        libros.forEach(System.out::println);
    }
    private void mostarTodosLosAutores() {
        autores = autorRepositorio.obtnerTodosLosAutores();
        autores.stream()
                .forEach(System.out::println);
    }

    private void buscarAutorPorAño(){
        System.out.println("Ingrese el año de nacimiento del autor:");
        Integer year = teclado.nextInt();
        teclado.nextLine(); // Limpiar el buffer
        List<Author> autores = autorRepositorio.findByAño(year);
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores nacidos en ese año.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Ingrese el idioma del libro (por ejemplo: en, es, fr):");
        String idioma = teclado.nextLine().toLowerCase();

        List<Libro> librosPorIdioma = libroRepositorio.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma.");
        } else {
            System.out.println("Libros en idioma '" + idioma + "':");
            for(Libro libro : librosPorIdioma) {

                System.out.println(libro);
            }
        }
    }

}
