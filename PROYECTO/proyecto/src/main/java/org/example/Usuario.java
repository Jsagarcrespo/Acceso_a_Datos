package org.example;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private String nombre;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

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
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public List<Pelicula> getPelicula() {
        return pelicula;
    }

    public void setPelicula(List<Pelicula> pelicula) {
        this.pelicula = pelicula;
    }

    private String alias;
    private String passw;

    public Usuario(int id, String nombre, String alias, String passw, String tel) {
        this.id = id;
        this.nombre = nombre;
        this.alias = alias;
        this.passw = passw;
        this.tel = tel;
        pelicula = new ArrayList<Pelicula>();
    }

    private String tel;

    private List<Pelicula> pelicula;




}
