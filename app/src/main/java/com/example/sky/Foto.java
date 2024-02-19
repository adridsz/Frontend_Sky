package com.example.sky;

import org.json.JSONException;
import org.json.JSONObject;

public class Foto {
    private String titulo;
    private String linkImagen;

    public Foto(JSONObject objeto) throws JSONException {
        this.titulo = objeto.getString("titulo");
        this.linkImagen = objeto.getString("image_url");
    }

    public String getTitulo() {
        return titulo;
    }

    public String getLinkImagen() {
        return linkImagen;
    }
}
