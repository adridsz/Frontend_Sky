package com.example.sky.ui.perfil.Carpetas;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sky.EditarCarpetaFragment;
import com.example.sky.R;
import com.example.sky.ui.perfil.Carpetas.CarpetasData;

public class CarpetasViewHolder extends RecyclerView.ViewHolder {
    private TextView nombre;
    private ImageView carpeta;
    private CarpetasData imagenes;
    public CarpetasViewHolder(@NonNull View itemView) {
        super(itemView);

        carpeta = (ImageView) itemView.findViewById(R.id.carpeta);
        nombre = (TextView) itemView.findViewById(R.id.nombre);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String carpeta = imagenes.getNombre();
                Context context = view.getContext();
                EditarCarpetaFragment editarCarpetaFragment = EditarCarpetaFragment.newInstance(carpeta);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_drawer, editarCarpetaFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void showData(CarpetasData data, Activity activity) {
        this.nombre.setText(data.getNombre());
        this.carpeta.setImageResource(data.getImagenCarpeta());
        this.imagenes = data;
    }
}
