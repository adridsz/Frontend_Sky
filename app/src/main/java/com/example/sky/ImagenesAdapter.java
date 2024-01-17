package com.example.sky;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImagenesAdapter extends RecyclerView.Adapter<ImagenesViewHolder> {
    private List<ImagenesData> todasLasFotos;
    private Activity activity;

    public ImagenesAdapter(List<ImagenesData> todasLasFotos, Activity activity) {
        this.todasLasFotos = todasLasFotos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ImagenesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_imagenes_view_holder, parent, false);
        return new ImagenesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenesViewHolder holder, int position) {
        ImagenesData dataToBeRendered = todasLasFotos.get(position);
        holder.showData(dataToBeRendered, activity);
    }

    @Override
    public int getItemCount() {
        return todasLasFotos.size();
    }
}
