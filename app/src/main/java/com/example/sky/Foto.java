package com.example.sky;

import org.json.JSONException;
import org.json.JSONObject;

public class Foto {
    private String titulo;
    private String linkImagen;

    // Constructor que toma un objeto JSONObject y extrae información para inicializar la Foto
    public Foto(JSONObject objeto) throws JSONException {
        this.titulo = objeto.getString("titulo");
        this.linkImagen = objeto.getString("image_url");
    }

    // Método para obtener el título de la foto
    public String getTitulo() {
        return titulo;
    }

    // Método para obtener el enlace a la imagen de la foto
    public String getLinkImagen() {
        return linkImagen;
    }
}
