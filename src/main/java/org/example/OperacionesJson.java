package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class OperacionesJson {

    public static void escribirListaObjetosJson(List<Libro> libros, Path ruta) {
        try {
            Files.deleteIfExists(ruta);
            ObjectMapper jsonMapper = new ObjectMapper();
            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonMapper.writeValue(ruta.toFile(), libros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Libro> leerArrayObjetosJson(Path ruta) {
        try {
            ObjectMapper jsonMapper = new ObjectMapper();
            return jsonMapper.readValue(ruta.toFile(), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Implementa una función que permita buscar libros por título o autor y mostrar los resultados.
    // Esta función busca si la lista contiene el texto buscado en el título o en el nombre del autor
    // ignorando mayúsculas

    public static List<Libro> buscarLibro(String tituloOAutor, List<Libro> listaLibros){
        String tituloOAutorMays = tituloOAutor.toUpperCase();
        List<Libro> libros = listaLibros.stream().filter(libro -> libro.getAutor().toUpperCase().contains(tituloOAutorMays)  || libro.getTitulo().toUpperCase().contains(tituloOAutorMays)).toList();
        return libros;
    }

    //Proporciona un menú interactivo para que el usuario pueda agregar nuevos libros, buscar libros y
    //ver la lista de todos los libros almacenados (ten en cuenta si debes permitir la opción de agregar un
    //libro ya existente)

    public static void menu(){
        boolean exit = false;
        ListaLibros listaLibros = new ListaLibros();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String opcion, tituloOautor;
        String isbn, titulo, autor, numPaginas = "", anhoPublicacion = "";

        boolean paginasOK = false;
        boolean anhoOK = false;

        while(!exit){
            System.out.println("Introduce una de las siguientes opciones:\n"+
                    "1. Agregar un libro\n"+
                    "2. Buscar un libro\n" +
                    "3. Mostrar la lista de libros.\n"+
                    "4. Salir\n");
            try {
                opcion = reader.readLine();

                switch (opcion){
                    case "1":
                        System.out.print("Introduce el isbn: ");
                        isbn = reader.readLine();

                        System.out.print("Introduce el título: ");
                        titulo = reader.readLine();

                        System.out.print("Introduce el autor: ");
                        autor = reader.readLine();

                        while(!paginasOK){
                            System.out.print("Introduce el numero de páginas: ");
                            numPaginas = reader.readLine();
                            try {
                                Integer.parseInt(numPaginas);
                                paginasOK = true;
                            } catch (NumberFormatException excepcion) {
                                System.out.println("El texto introducido no es un entero.");
                            }
                        }

                        while(!anhoOK){
                            System.out.print("Introduce el año de publicación: ");
                            anhoPublicacion = reader.readLine();
                            try {
                                Integer.parseInt(anhoPublicacion);
                                anhoOK = true;
                            } catch (NumberFormatException excepcion) {
                                System.out.println("El texto introducido no es un entero.");
                            }
                        }
                        Libro libro = new Libro(isbn, titulo, autor, Integer.parseInt(numPaginas), Integer.parseInt(anhoPublicacion));
                        listaLibros.agregarUnLibro(libro);

                        break;
                    case "2":
                        System.out.print("Introduce el título o autor a buscar: ");
                        tituloOautor = reader.readLine();
                        if(buscarLibro(tituloOautor, listaLibros.getListaLibros()).size() > 0){
                            System.out.println("Estas son las coincidencias encontradas: ");
                            listaLibros.getListaLibros().forEach(System.out::println);
                        } else{
                            System.out.println("No se han encontrado coincidencias.");
                        }
                        break;
                    case "3":
                        if(listaLibros.getListaLibros().size() > 0){
                            System.out.println("Imprimiendo la lista de libros");
                            listaLibros.getListaLibros().forEach(System.out::println);
                        } else {
                            System.out.println("La lista está vacía.");
                            listaLibros.getListaLibros().forEach(System.out::println);
                        }

                        break;
                    case "4":
                        exit = true;
                        System.out.println("Saliendo de la aplicación.");
                        break;
                    default:
                        System.out.println("Opción incorrecta.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }



}
