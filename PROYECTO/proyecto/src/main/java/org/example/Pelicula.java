package org.example;

import java.io.Serializable;
import java.util.List;

public class Pelicula implements Serializable {

    private String director;
    private String descripcion;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    private String genero;
    private int anio;



    private Usuario usuario;

    public Pelicula(String titulo, String director, String descripcion, String genero, int anio) {
        this.titulo = titulo;
        this.director = director;
        this.descripcion = descripcion;
        this.anio = anio;
        this.genero = genero;
    }

    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }


    public void setUsuario(Usuario usuario) {
        usuario = usuario;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'';
    }
}
