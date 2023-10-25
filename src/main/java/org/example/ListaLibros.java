package org.example;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
@JacksonXmlRootElement
public class ListaLibros {
    @JacksonXmlElementWrapper(localName = "libros")
    @JacksonXmlProperty(localName = "libro")
    private List<Libro> libros;


    public ListaLibros() {
        this.libros = new ArrayList<>();
    }

    public void agregarUnLibro(Libro libro){
        this.libros.add(libro);
    }

    public List<Libro> getListaLibros(){
        return this.libros;
    }




}
