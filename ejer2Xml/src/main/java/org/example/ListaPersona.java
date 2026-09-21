package org.example;

import java.util.ArrayList;
import java.util.List;

public class ListaPersona {


    private List<Persona> lista = new ArrayList<>();

    public ListaPersona() {
    }

    public void add(Persona persona) {
        lista.add(persona);
    }

    public List<Persona> getListaPersona() {
        return lista;
    }


}
