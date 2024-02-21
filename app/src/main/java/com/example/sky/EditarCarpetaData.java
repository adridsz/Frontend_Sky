package com.example.sky;

public class EditarCarpetaData {
    private String url1;
    private String url2;

    public EditarCarpetaData(String imagenurl1, String imagenurl2) {
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
