package com.example.sky.ui.perfil;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sky.R;

import java.util.List;

public class CarpetasAdapter extends RecyclerView.Adapter<CarpetasViewHolder> {
    private List<CarpetasData> todasLasCarpetas;
    private Activity activity;

    public CarpetasAdapter(List<CarpetasData> todasLasCarpetas, Activity activity) {
        this.todasLasCarpetas = todasLasCarpetas;
        this.activity = activity;
    }
    @NonNull
    @Override
    public CarpetasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_carpetas_view_holder, parent, false);
        return new CarpetasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CarpetasViewHolder holder, int position) {
        CarpetasData dataToBeRendered = todasLasCarpetas.get(position);
        holder.showData(dataToBeRendered, activity);
    }

    @Override
    public int getItemCount() {
        return todasLasCarpetas.size();
    }
}
