# 📚 Proyecto de Literatura - Gutendex API

Este proyecto en Java permite explorar libros y autores a través de la API pública de [Gutendex](https://gutendex.com/), almacenando los datos en una base local y ofreciendo funcionalidades interactivas por consola.

## 🚀 Funcionalidades

- 🔍 Buscar libros por título y registrarlos en la base de datos
- 📖 Listar todos los libros registrados
- 🧑‍🎓 Listar todos los autores registrados
- 📅 Buscar autores por año de nacimiento
- 🌐 Filtrar libros por idioma

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot (para la capa de persistencia)
- JPA / Hibernate
- Gutendex API
- Maven

## 📦 Estructura del proyecto

com.diego.literatura 
    ├── principal # Clase Principal con menú interactivo 
    ├── model # Entidades: Libro, Author, GutendexResponse 
    ├── repository # Interfaces de acceso a datos 
    ├── service # Consumo de API y conversión de datos

## 🧪 Ejecución

1. Clona el repositorio:
   ```bash
   git clone https://github.com/Diegith/nombre-del-repositorio.git
   ```
2. Ejecuta la clase Principal desde tu IDE o terminal.

3. Interactúa con el menú para explorar libros y autores.


## 📚 Ejemplo de uso
---------------------------
Seleccione una opción (1-5):
---------------------------
1 - Buscar libro por título
2 - Listar libros registrados
3 - Listar autores registrados
4 - Buscar autor por año de nacimiento
5 - Listar libros por idioma
0 - Salir

## 📌 Notas
Los libros y autores se obtienen desde la API de Gutendex y se almacenan localmente si no existen previamente.

La búsqueda por idioma utiliza códigos ISO (por ejemplo: en, es, fr).

📝 Licencia
Este proyecto está bajo la licencia MIT. Puedes usarlo, modificarlo y distribuirlo libremente.