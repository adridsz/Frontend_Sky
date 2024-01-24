package com.example.sky.ui.perfil.EliminarCarpeta;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sky.R;
import com.example.sky.ui.perfil.Carpetas.CarpetasData;

import java.util.List;

public class ECAdapter extends RecyclerView.Adapter<ECViewHolder> {
    private Activity activity;
    private List<CarpetasData> todasLasCarpetas;
    private int posicionSeleccionada = RecyclerView.NO_POSITION; //esto detecta si se ha seleccionado alguna casilla del RadioButton, si no se seleeciono ninguna seguira siendo nula

    public ECAdapter(List<CarpetasData> todasLasCarpetas, Activity activity) {
        this.todasLasCarpetas = todasLasCarpetas;
        this.activity = activity;
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
        CarpetasData dataToBeRendered = todasLasCarpetas.get(position);
        holder.showData(dataToBeRendered, activity);
    }

    @Override
    public int getItemCount() {
        return todasLasCarpetas.size();
    }

    public void setPosicionSeleccionada(int posicionSeleccionada) {
        this.posicionSeleccionada = posicionSeleccionada;
        notifyDataSetChanged();
    }

    public int getPosicionSeleccionada() {
        return posicionSeleccionada;
    }

    public void eliminarCarpetaSeleccionada() {
        if (posicionSeleccionada != RecyclerView.NO_POSITION) { //esto quiere decir: si la posicion seleccionada no es nula
            todasLasCarpetas.remove(posicionSeleccionada);
            posicionSeleccionada = RecyclerView.NO_POSITION; //lo vuelvo a fijar a nulo para q la siguiente vez funcione
            notifyDataSetChanged();
        }
    }
}
