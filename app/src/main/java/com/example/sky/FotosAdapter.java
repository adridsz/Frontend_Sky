package com.example.sky;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sky.Foto;
import java.util.List;

public class FotosAdapter extends RecyclerView.Adapter<FotosAdapter.ViewHolder> {

    private List<Foto> listaFotos;

    public FotosAdapter(List<Foto> listaFotos) {
        this.listaFotos = listaFotos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Foto foto = listaFotos.get(position);
        holder.textViewTituloFoto.setText(foto.getTitulo());

        // Cargar la imagen con Glide
        Glide.with(holder.itemView)
                .load(foto.getLinkImagen())
                .into(holder.imageViewFoto);
    }


    @Override
    public int getItemCount() {
        return listaFotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTituloFoto;
        private ImageView imageViewFoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTituloFoto = itemView.findViewById(R.id.textViewTituloFoto);
            imageViewFoto = itemView.findViewById(R.id.imageViewFoto); // Encuentra el ImageView en el layout
        }
    }
}