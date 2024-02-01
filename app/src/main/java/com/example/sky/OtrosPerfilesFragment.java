package com.example.sky;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class OtrosPerfilesFragment extends Fragment {

    private boolean mostrarImagenes = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_otros_perfiles, container, false);

        // Obtén referencias a las vistas
        ImageView fotoPerfil = view.findViewById(R.id.fotoPerfil);
        TextView nombreUsuario = view.findViewById(R.id.nombreUsuario);
        TextView descripcionUsuario = view.findViewById(R.id.descripcionUsuario);
        Button btnImagenes = view.findViewById(R.id.btnImagenes);
        Button btnCarpetas = view.findViewById(R.id.btnCarpetas);

        // Configura la imagen de perfil y el nombre de usuario
        fotoPerfil.setImageResource(R.drawable.ic_launcher_foreground); // Reemplaza con la lógica para cargar la foto
        nombreUsuario.setText("Nombre de usuario"); // Reemplaza con la lógica para cargar el nombre

        // Configura la descripción del usuario
        descripcionUsuario.setText("Descripción del usuario...");

        // Maneja los clics en los botones de imágenes y carpetas
        btnImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarImagenes = true;
                showToast("Botón Imágenes pulsado");
                // Agregar lógica para cargar imágenes del usuario
            }
        });

        btnCarpetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarImagenes = false;
                showToast("Botón Carpetas pulsado");
                // Agregar lógica para cargar carpetas del usuario
            }
        });

        // Configura la visibilidad de las vistas según la selección actual
        if (mostrarImagenes) {
            // Muestra las imágenes y oculta las carpetas
        } else {
            // Muestra las carpetas y oculta las imágenes
        }

        return view;
    }

    // Método para mostrar Toast
    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
