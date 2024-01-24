package com.example.sky.ui.perfil.Imagenes;

import org.json.JSONException;
import org.json.JSONObject;

public class ImagenesData {
    private String url1;
    private String url2;

    public ImagenesData(String imagenurl1, String imagenurl2) {
        url1 = imagenurl1;
        url2 = imagenurl2;
    }

    public String getImageUrl() {
        return url1;
    }
    public String getImageUrl2(){
        return url2;
    }
}
