package com.example.sky;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.sky.ui.perfil.PerfilFragment;

import java.util.ArrayList;

public class EliminarFotoFragment extends Fragment {

    private ListView listViewFotos;
    private Button btnCancelar;
    private Button btnConfirmar;

    private ArrayList<String> fotos;
    private ArrayAdapter<String> adapter;
    private boolean[] fotosSeleccionadas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_eliminar_foto, container, false);

        listViewFotos = rootView.findViewById(R.id.listViewFotos);
        btnCancelar = rootView.findViewById(R.id.btnCancelar);
        btnConfirmar = rootView.findViewById(R.id.btnConfirmar);

        // Lista de fotos
        fotos = new ArrayList<>();
        fotos.add("Foto 1");
        fotos.add("Foto 2");
        fotos.add("Foto 3");

        // Inicializar el array para almacenar las selecciones
        fotosSeleccionadas = new boolean[fotos.size()];

        // Adaptador para la lista de fotos
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_multiple_choice, fotos);
        listViewFotos.setAdapter(adapter);
        listViewFotos.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Manejar clics en la lista para actualizar el array de selecciones
        listViewFotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fotosSeleccionadas[position] = !fotosSeleccionadas[position];
            }
        });

        // Botón Cancelar: desmarcar todas las selecciones y retroceder a la pantalla anterior
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarSelecciones();
                volverAMainActivity();
            }
        });

        // Botón Confirmar: eliminar fotos seleccionadas
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarFotosSeleccionadas();
            }
        });

        return rootView;
    }

    private void limpiarSelecciones() {
        for (int i = 0; i < fotosSeleccionadas.length; i++) {
            fotosSeleccionadas[i] = false;
        }
        actualizarLista();
    }

    private void eliminarFotosSeleccionadas() {
        for (int i = fotosSeleccionadas.length - 1; i >= 0; i--) {
            if (fotosSeleccionadas[i]) {
                fotos.remove(i);
            }
        }
        limpiarSelecciones();
        Toast.makeText(requireContext(), "Fotos eliminadas", Toast.LENGTH_SHORT).show();
    }

    private void actualizarLista() {
        adapter.notifyDataSetChanged();
        for (int i = 0; i < fotosSeleccionadas.length; i++) {
            listViewFotos.setItemChecked(i, fotosSeleccionadas[i]);
        }
    }

    private void volverAMainActivity() {
        Fragment myfragment3 = new PerfilFragment();
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_drawer, myfragment3).commit();
    }
}
