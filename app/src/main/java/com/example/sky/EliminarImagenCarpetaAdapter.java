package com.example.sky;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EliminarImagenCarpetaAdapter extends RecyclerView.Adapter<EliminarImagenCarpetaViewHolder> {
    private List<EliminarImagenCarpetaData> imagenes;
    private Activity activity;
    public EliminarImagenCarpetaAdapter(List<EliminarImagenCarpetaData> imagenes, Activity activity) {
        this.imagenes = imagenes;
        this.activity = activity;
    }
    @NonNull
    @Override
    public EliminarImagenCarpetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_eliminar_imagen_carpeta_view_holder, parent, false);
        return new EliminarImagenCarpetaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EliminarImagenCarpetaViewHolder holder, int position) {
        EliminarImagenCarpetaData dataToBeRendered = imagenes.get(position);
        holder.showData(dataToBeRendered, activity);
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }
}
