package com.example.sky.ui.perfil.Carpetas;

import org.json.JSONException;
import org.json.JSONObject;

public class CarpetasData {
    private String nombre; //las carpetas van a tener una imagen determinada, solo se recupera el nombre
    private int imagenCarpeta;

    public CarpetasData(String nombre, int imagenCarpeta) {
        this.nombre = nombre;
        this.imagenCarpeta = imagenCarpeta;


    }
    public String getNombre() {
        return nombre;
    }

    public int getImagenCarpeta() {
        return imagenCarpeta;
    }
}