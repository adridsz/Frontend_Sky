package com.example.sky;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class EliminarImagenCarpetaViewHolder extends RecyclerView.ViewHolder {
    private ImageView imagen;
    private ImageView imagen2;
    private CheckBox checkBox;
    private CheckBox checkBox2;
    private EliminarImagenCarpetaData imagenes;

    public EliminarImagenCarpetaViewHolder(@NonNull View itemView) {
        super(itemView);

        imagen = (ImageView) itemView.findViewById(R.id.imagen);
        imagen2 = (ImageView) itemView.findViewById(R.id.imagen2);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) itemView.findViewById(R.id.checkBox2);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, "Clicaste en la primera foto", Toast.LENGTH_LONG).show();
                //cambiar a actividad de mostrar info de la foto
                Intent intent = new Intent(context, Imagen.class);
                intent.putExtra("image_url", imagenes.getImageUrl());
                context.startActivity(intent);
            }
        });

        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, "Clicaste en la segunda foto", Toast.LENGTH_LONG).show();
                //cambiar a actividad de mostrar info de la foto
                Intent intent = new Intent(context, Imagen.class);
                intent.putExtra("image_url", imagenes.getImageUrl2());
                context.startActivity(intent);
            }
        });
    }

    public void showData(EliminarImagenCarpetaData data, Activity activity){
        Glide.with(itemView.getContext())
                .load(data.getImageUrl())
                .into(this.imagen);
        Glide.with(itemView.getContext())
                .load(data.getImageUrl2())
                .into(this.imagen2);
        this.imagenes = data;

        // Restablecer el estado de los CheckBoxes cuando se reutiliza el ViewHolder
        checkBox.setChecked(false);
        checkBox2.setChecked(false);
    }
}