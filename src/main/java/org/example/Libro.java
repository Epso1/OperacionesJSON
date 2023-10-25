package org.example;

import lombok.Getter;
@Getter


public class Libro {

    private String isbn;

    private String titulo;

    private String autor;

    private int numPaginas;

    private int anhoPublicacion;


    public Libro(String isbn, String titulo, String autor, int numPaginas, int anhoPublicacion) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.anhoPublicacion = anhoPublicacion;
    }
    public Libro(){

    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public int getAnhoPublicacion() {
        return anhoPublicacion;
    }
    @Override
    public String toString(){
        return "Libro{" +
                "isbn: " + isbn + " - " +
                "titulo: " + titulo +  " - " +
                "autor: " + autor + " - " +
                "número de páginas: " + numPaginas  + " - " +
                "año de publicación: " + anhoPublicacion + "}";
    }
}
