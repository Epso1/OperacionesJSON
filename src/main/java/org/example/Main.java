package org.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Libro lib1 = new Libro("1021021021", "El Quijote", "Miguel de Cervantes", 455, 1605);
        Libro lib2 = new Libro("7771021021", "Mi libro", "Anónimo", 500, 1910);
        Libro lib3 = new Libro("7898745632", "Historias para no dormir", "José Aguilar", 125, 2010);

        ListaLibros lista = new ListaLibros();

        lista.agregarUnLibro(lib1);
        lista.agregarUnLibro(lib2);
        lista.agregarUnLibro(lib3);

        System.out.println("Escribiendo archivo json");
        OperacionesJson.escribirListaObjetosJson(lista.getListaLibros(), Path.of(".", "src", "main", "java","org","example","libros.json"));
        System.out.println();

        System.out.println("Creando lista desde archivo json");
        List<Libro> librosDeserializados = OperacionesJson.leerArrayObjetosJson(Path.of(".", "src", "main", "java","org","example", "libros.json" ));
        System.out.println();

        System.out.println("Imprimiendo lista creada desde archivo json");
        librosDeserializados.forEach(System.out::println);
        System.out.println();

        System.out.println("Probando a buscar 'El Quijote'");
        OperacionesJson.buscarLibro("El Quijote", lista.getListaLibros()).forEach(System.out::println);
        System.out.println();


        System.out.println("Lanzando el menú.");
        OperacionesJson.menu();

    }
}