package com.example.sky;

import org.json.JSONException;
import org.json.JSONObject;

public class ImagenesData {
    private String imageUrl;

    public ImagenesData(JSONObject json) {
        try{
            this.imageUrl = json.getString("image_url"); //cambiar al nombre q le pongamos en el json
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
