package com.example.sky;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class BuscadorFragment extends Fragment {

    private EditText editTextBuscador;
    private ImageButton buttonBuscar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos la vista del fragmento
        View view = inflater.inflate(R.layout.activity_buscador, container, false);

        // Asociamos las variables con los elementos de la interfaz de usuario en el diseño
        editTextBuscador = view.findViewById(R.id.editTextBuscador);
        buttonBuscar = view.findViewById(R.id.buttonBuscar);

        // Establecemos un detector de clics en el botón de búsqueda
        buttonBuscar.setOnClickListener(v -> {
            // Obtenemos el texto del EditText
            String searchText = editTextBuscador.getText().toString();
            // Aquí agregaremos a futuro el codigo del buscador
        });

        // Retornamos la vista
        return view;
    }
}