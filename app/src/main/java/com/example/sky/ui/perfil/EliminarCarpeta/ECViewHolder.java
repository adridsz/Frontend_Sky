package com.example.sky.ui.perfil.EliminarCarpeta;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.R;
import com.example.sky.ui.perfil.Carpetas.CarpetasData;

public class ECViewHolder extends RecyclerView.ViewHolder {
    private TextView nombre;
    private CarpetasData imagenes;
    public ECViewHolder(@NonNull View itemView) {
        super(itemView);

        nombre = (TextView) itemView.findViewById(R.id.nombre);
    }

    public void showData(CarpetasData data, Activity activity) {
        this.nombre.setText(data.getNombre());
        this.imagenes = data;
    }


}