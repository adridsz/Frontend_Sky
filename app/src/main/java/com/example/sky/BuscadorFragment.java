package com.example.sky;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.sky.login.LoginActivity;
import com.example.sky.ui.inicio.InicioFragment;

public class BuscadorFragment extends Fragment {

    private EditText editTextBuscador;
    private ImageView buttonBuscar;
    private Button salir;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos la vista del fragmento
        View view = inflater.inflate(R.layout.fragment_buscador, container, false);

        // Asociamos las variables con los elementos de la interfaz de usuario en el diseño
        editTextBuscador = view.findViewById(R.id.editTextBuscador);
        buttonBuscar = view.findViewById(R.id.buttonBuscar);
        salir = view.findViewById(R.id.buttonVolver);

        salir.setOnClickListener(v -> {
            salir();
        });

        // Retornamos la vista
        return view;
    }


    private void salir() {
        Intent intent = new Intent(getActivity(), Drawer.class);
        startActivity(intent);
    }

}