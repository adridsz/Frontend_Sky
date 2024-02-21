package com.example.sky.ui.inicio;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sky.Imagen;
import com.example.sky.R;

import java.util.List;

public class ImagenRecyclerViewAdaptar extends RecyclerView.Adapter<ImagenViewHolder> { //esto es para q me muestre el adaptador
    private List<ImagenData> ImagenList;
    private Activity activity;

    public ImagenRecyclerViewAdaptar(List<ImagenData> pecesDataList, Activity activity) { //esto es para q me muestre la lista
        this.ImagenList = pecesDataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ImagenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //esto es para q me muestre el viewholder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_imagen_view_holder, parent, false);
        return new ImagenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenViewHolder holder, int position) { //esto es para q me muestre los datos
        ImagenData ImagenData = ImagenList.get(position);
        holder.showData(ImagenData, activity);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtenemos el elemento de datos en la posición clicada
                int adapterPosition = holder.getAdapterPosition();
                ImagenData elementoClicado = ImagenList.get(adapterPosition);

                //Instanciamos la actividad
                Intent intent = new Intent(activity, Imagen.class);

                //Utilizamos putExtra para pasar los datos a la actividad Cancion
                intent.putExtra("image_url", elementoClicado.getImage_url());
                //Iniciamos la actividad
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //esto es para q me muestre el itemcount
        return ImagenList.size();
    }
}