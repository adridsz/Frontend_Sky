package com.example.sky.ui.inicio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.sky.R;

public class ImagenViewHolder extends RecyclerView.ViewHolder { //esto es para q me muestre el viewholder
    private ImageView imageView;
    private ImagenData imagenData;

    private Context context;


    public ImagenViewHolder(@NonNull View itemView) { //esto es para q me muestre el itemview
        super(itemView);
        this.context = context;
        imageView = (ImageView) itemView.findViewById(R.id.img_celda);

        itemView.setOnClickListener(new View.OnClickListener() { //vamos a hacer un clicklistener con esto mostramos el itemview
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Detail_Activity.class);
                intent.putExtra("imagen", imagenData.getImage_url());

                context.startActivity(intent);
            }
        });
    }

    public void showData(ImagenData imgData, Activity activity) { //esto es para q me muestre el showdata
        this.imagenData = imgData; // Guarda la imagenData
        Glide.with(itemView.getContext())//esto es para q me muestre la imagen
                .load(imagenData.getImage_url())
                .into(imageView);//esto es para q me muestre la imagen
    }
}