package com.example.sky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ImagenesViewHolder extends RecyclerView.ViewHolder {
    private ImageView imagen;
    private ImagenesData imagenes;

    public ImagenesViewHolder(@NonNull View itemView) {
        super(itemView);

        imagen = (ImageView) itemView.findViewById(R.id.imagen);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Toast.makeText(context, "Clicaste en una foto", Toast.LENGTH_LONG).show(); //cambiar a actividad de mostrar info de la foto
            }
        });
    }

    public void showData(ImagenesData data, Activity activity){
        Glide.with(itemView.getContext())
                .load(data.getImageUrl())
                .into(this.imagen);
        this.imagenes = data;
    }

}