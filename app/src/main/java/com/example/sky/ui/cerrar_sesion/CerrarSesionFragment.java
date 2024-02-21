package com.example.sky.ui.cerrar_sesion;

import com.example.sky.Drawer;
import com.example.sky.R;
import com.example.sky.login.LoginActivity;
import com.example.sky.ui.inicio.InicioFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class CerrarSesionFragment extends Fragment {

    @Nullable
    @Override
    // Método llamado cuando se crea la vista del fragmento
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el diseño del fragmento_cerrar_sesion.xml
        View view = inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);

        // Obtener referencias a las vistas en el diseño del fragmento
        TextView tvMessage = view.findViewById(R.id.tvMessage);
        Button btnConfirmar = view.findViewById(R.id.btnConfirmar);
        Button btnCancelar = view.findViewById(R.id.btnCancelar);

        // Manejar clic en el botón Confirmar
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad
                Intent intent = new Intent(getActivity(), LoginActivity.class);

                // Obtener el FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Limpiar la pila de fragmentos para que no haya fragmentos en la parte superior
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // Obtener el AppCompatActivity y finalizarlo
                AppCompatActivity activity = (AppCompatActivity) requireActivity();
                startActivity(intent);
                activity.finishAffinity();
            }
        });

        // Manejar clic en el botón Cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad
                Intent intent = new Intent(getActivity(), Drawer.class);

                // Obtener el FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Limpiar la pila de fragmentos para que no haya fragmentos en la parte superior
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                // Obtener el AppCompatActivity y finalizarlo
                AppCompatActivity activity = (AppCompatActivity) requireActivity();
                startActivity(intent);
                activity.finishAffinity();
            }
        });

        return view; // Devolver la vista inflada
    }
}

