package com.example.sky.ui.perfil.EliminarCarpeta;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.R;
import com.example.sky.ui.perfil.Carpetas.CarpetasData;

public class ECViewHolder extends RecyclerView.ViewHolder {
    private TextView nombre;
    private ECData imagenes;
    public CheckBox checkBox;
    public ECViewHolder(@NonNull View itemView) {
        super(itemView);

        nombre = (TextView) itemView.findViewById(R.id.nombre);
        checkBox = (CheckBox) itemView.findViewById(R.id.casilla);
    }

    public void showData(ECData data, Activity activity) {
        this.nombre.setText(data.getNombre());
        this.imagenes = data;
    }


}