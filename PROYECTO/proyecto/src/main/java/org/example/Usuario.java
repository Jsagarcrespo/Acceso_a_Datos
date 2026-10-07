package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuario implements Serializable {

    // Necesario para que lo podamos guardar el objeto en el .dat mas tarde
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;


    public Usuario(int id, String nombre, int tel, String passwd) {
        this.id = id;
        this.nombre = nombre;
        this.tel = tel;
        this.passwd = passwd;
    }

    private String passwd;
    private int tel;

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    private List<Pelicula> peliculas;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassw() {
        return passwd;
    }

    public void setPassw(String passw) {
        this.passwd = passw;
    }

    public List<Pelicula> getPelicula() {
        return pelicula;
    }

    public void setPelicula(List<Pelicula> pelicula) {
        this.pelicula = pelicula;
    }

    private List<Pelicula> pelicula;


    @Override
    public String toString() {
        return " nombre='" + nombre + '\'' ;
    }
}
