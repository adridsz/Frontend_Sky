package com.example.sky.ui.inicio;

import org.json.JSONException;
import org.json.JSONObject;

public class ImagenData {
    private String image_url;

    public ImagenData(JSONObject json) {
        try{
            this.image_url = json.getString("image_url");//esto es para q me muestre la imagen
        }catch (JSONException e) {
            e.printStackTrace(); //esto es para q si hay un error me lo muestre
        }
    }

    public String getImage_url() {
        return image_url;
    }


}
