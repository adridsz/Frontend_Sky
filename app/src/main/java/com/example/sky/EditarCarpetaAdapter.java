package com.example.sky;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sky.ui.perfil.Carpetas.CarpetasData;
import com.example.sky.ui.perfil.Carpetas.CarpetasViewHolder;
import com.example.sky.ui.perfil.Imagenes.ImagenesData;
import com.example.sky.ui.perfil.Imagenes.ImagenesViewHolder;

import java.util.List;

public class EditarCarpetaAdapter extends RecyclerView.Adapter<EditarCarpetaViewHolder> {
    private List<EditarCarpetaData> imagenes;
    private Activity activity;
    public EditarCarpetaAdapter(List<EditarCarpetaData> imagenes, Activity activity) {
        this.imagenes = imagenes;
        this.activity = activity;
    }
    @NonNull
    @Override
    public EditarCarpetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_editar_carpetas_view_holder, parent, false);
        return new EditarCarpetaViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EditarCarpetaViewHolder holder, int position) {
        EditarCarpetaData dataToBeRendered = imagenes.get(position);
        holder.showData(dataToBeRendered, activity);
    }

    @Override
    public int getItemCount() {
        return imagenes.size();
    }
}
