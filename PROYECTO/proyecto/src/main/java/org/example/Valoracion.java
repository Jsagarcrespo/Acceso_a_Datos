package org.example;

import java.io.Serializable;

public class Valoracion implements Serializable {

    private static final long serialVersionUID = 1L;

    private int puntuacion;
    private String comentario;

    public Valoracion(int puntuacion, String comentario) {
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "puntuacion=" + puntuacion + ", comentario='" + comentario + '\'';
    }
}
