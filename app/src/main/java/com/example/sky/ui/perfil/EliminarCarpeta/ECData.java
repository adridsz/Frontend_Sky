package com.example.sky.ui.perfil.EliminarCarpeta;

public class ECData {
    private boolean isChecked;
    private String nombre_carpeta;

    public ECData(String nombre_carpeta){
        this.nombre_carpeta = nombre_carpeta;
        this.isChecked = false;
    }

    public String getNombre() {
        return nombre_carpeta;
    }

    //obtener valor
    public boolean isChecked() {
        return isChecked;
    }

    //determinar valor
    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
