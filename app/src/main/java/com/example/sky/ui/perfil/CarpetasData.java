package com.example.sky.ui.perfil;

import org.json.JSONException;
import org.json.JSONObject;

public class CarpetasData {
    private String nombre; //las carpetas van a tener una imagen determinada, solo se recupera el nombre
    public CarpetasData(JSONObject json) {
        try{
            this.nombre = json.getString("name"); //acordarse de cambiar al hacer el json
        }catch(JSONException e) {
            e.printStackTrace();
        }
    }
    public String getNombre() {
        return nombre;
    }
}
