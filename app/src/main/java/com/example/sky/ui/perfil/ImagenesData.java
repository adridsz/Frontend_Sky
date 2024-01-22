package com.example.sky.ui.perfil;

import org.json.JSONException;
import org.json.JSONObject;

public class ImagenesData {
    private String url1;
    private String url2;

    public ImagenesData(JSONObject json) {
        try{
            this.url1 = json.getString("image_url"); //cambiar al nombre q le pongamos en el json
            this.url2 = json.getString("image_url");
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getImageUrl() {
        return url1;
    }
    public String getImageUrl2(){
        return url2;
    }
}
