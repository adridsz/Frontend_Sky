package com.example.sky.ui.perfil.Imagenes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sky.R;
import com.example.sky.ui.perfil.Imagenes.ImagenesData;

public class ImagenesViewHolder extends RecyclerView.ViewHolder {
    private ImageView imagen;
    private ImageView imagen2;
    private ImagenesData imagenes;

    public ImagenesViewHolder(@NonNull View itemView) {
        super(itemView);

        imagen = (ImageView) itemView.findViewById(R.id.imagen);
        imagen2 = (ImageView) itemView.findViewById(R.id.imagen2);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, "Clicaste en la primera foto", Toast.LENGTH_LONG).show(); //cambiar a actividad de mostrar info de la foto
            }
        });

        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, "Clicaste en la segunda foto", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showData(ImagenesData data, Activity activity){
        Glide.with(itemView.getContext())
                .load(data.getImageUrl())
                .into(this.imagen);
        Glide.with(itemView.getContext())
                .load(data.getImageUrl2())
                .into(this.imagen2);
        this.imagenes = data;
    }
}