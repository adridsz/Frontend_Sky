package com.example.sky;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CarpetasViewHolder extends RecyclerView.ViewHolder {
    private TextView nombre;
    private ImageView carpeta;
    private CarpetasData imagenes;
    public CarpetasViewHolder(@NonNull View itemView) {
        super(itemView);

        carpeta = (ImageView) itemView.findViewById(R.id.carpeta);
        nombre = (TextView) itemView.findViewById(R.id.nombre);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, "Entraste en una carpeta", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showData(CarpetasData data, Activity activity) {
        this.nombre.setText(data.getNombre());
        this.imagenes = data;
    }
}
