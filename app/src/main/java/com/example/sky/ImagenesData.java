package com.example.sky;

import org.json.JSONException;
import org.json.JSONObject;

public class ImagenesData {
    private String name;
    private String imageUrl;

    public ImagenesData(JSONObject json) {
        try{
            this.name = json.getString("name"); //acordarse de camcirlo al nombre que le pongamos en el json
            this.name = json.getString("image_url");
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}
