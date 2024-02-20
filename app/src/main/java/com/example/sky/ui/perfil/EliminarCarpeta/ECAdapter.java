package com.example.sky.ui.perfil.EliminarCarpeta;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

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

        //esto se encarga de leer si el usuario selecciono o no la casilla
        holder.checkBox.setChecked(todasLasCarpetas.get(holder.getAdapterPosition()).isChecked());

        //esto actualiza el estado del isChecked en caso de q el usuario seleccionara la casilla
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataToBeRendered.setChecked(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return todasLasCarpetas.size();
    }



    public List<ECData> getSelected(){
        List<ECData> carpetasSeleccionadas = new ArrayList<>();

        for (ECData carpeta : todasLasCarpetas) {
            if (carpeta.isChecked()) {
                carpetasSeleccionadas.add(carpeta);
            }
        }
        return carpetasSeleccionadas;
    }

    public List<ECData> getNotSelected() {
        List<ECData> carpetasNoSeleccionadas = new ArrayList<>();

        for (ECData carpeta : todasLasCarpetas) {
            if (!carpeta.isChecked()) {
                carpetasNoSeleccionadas.add(carpeta);
            }
        }
        return carpetasNoSeleccionadas;
    }

    public void eliminarCarpetas(){
        List<ECData> carpetasSeleccionadas = getSelected();

        // Aquí implementa la lógica para eliminar las carpetas seleccionadas
        for (ECData carpeta : carpetasSeleccionadas) {
            todasLasCarpetas.remove(carpeta);
        }

        // Notifica al adaptador que los datos han cambiado
        notifyDataSetChanged();
    }
}
