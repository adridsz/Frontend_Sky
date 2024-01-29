package com.example.sky.ui.perfil.EliminarCarpeta;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sky.R;
import com.example.sky.ui.perfil.Carpetas.CarpetasData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ECAdapter extends RecyclerView.Adapter<ECViewHolder> {
    private Activity activity;
    private List<ECData> todasLasCarpetas;
    //private List<Boolean> carpetasSeleccionadas;  //lista para almacenar el estado de selección de cada carpeta

    public ECAdapter(List<ECData> todasLasCarpetas, Activity activity) {
        this.todasLasCarpetas = todasLasCarpetas;
        this.activity = activity;
        //this.carpetasSeleccionadas = new ArrayList<>(Collections.nCopies(todasLasCarpetas.size(), false));
    }
    @NonNull
    @Override
    public ECViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_ecview_holder, parent, false);
        return new ECViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ECViewHolder holder, int position) {
        ECData dataToBeRendered = todasLasCarpetas.get(position);
        holder.showData(dataToBeRendered, activity);

        holder.checkBox.setChecked(todasLasCarpetas.get(holder.getAdapterPosition()).isChecked());

    }

    @Override
    public int getItemCount() {
        return todasLasCarpetas.size();
    }


    /*public void eliminarCarpetaSeleccionada() {
        for (int i = carpetasSeleccionadas.size() - 1; i >= 0; i--) {
            if (carpetasSeleccionadas.get(i)) {
                todasLasCarpetas.remove(i);
                carpetasSeleccionadas.remove(i);
            }
        }
        notifyDataSetChanged();
    }*/

    public List<ECData> getSelected(){
        List<ECData> carpetasSeleccionadas = new ArrayList<>();
        for (ECData carpeta : todasLasCarpetas) {
            if (carpeta.isChecked()) {
                carpetasSeleccionadas.add(carpeta);
            }
        }
        return carpetasSeleccionadas;
    }
}
