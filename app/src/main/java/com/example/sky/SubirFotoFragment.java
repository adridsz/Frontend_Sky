package com.example.sky;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.sky.ui.perfil.PerfilFragment;

public class SubirFotoFragment extends Fragment {

    // Método llamado cuando se crea la vista del fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento_subir_foto.xml
        View rootView = inflater.inflate(R.layout.fragment_subir_foto, container, false);

        // Configurar el Spinner con las categorías
        Spinner spinnerGenero = rootView.findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(adapter);

        // Configurar el botón "Confirmar"
        Button btnConfirmar = rootView.findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Verificar campos antes de confirmar y mostrar mensajes Toast
                if (validarCampos()) {
                    Toast.makeText(requireContext(), "Foto subida con éxito", Toast.LENGTH_SHORT).show();
                    volverAPerfil();
                } else {
                    Toast.makeText(requireContext(), "Faltan campos obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configurar el botón "Volver"
        Button btnVolver = rootView.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volverAPerfil();
            }
        });

        return rootView; // Devolver la vista inflada
    }

    // Método para validar los campos antes de confirmar
    private boolean validarCampos() {
        EditText editTextTituloFoto = requireView().findViewById(R.id.editTextTituloFoto);
        Spinner spinnerCategoria = requireView().findViewById(R.id.spinnerCategoria);
        EditText editTextLinkFoto = requireView().findViewById(R.id.editTextLinkFoto);

        return !editTextTituloFoto.getText().toString().isEmpty() &&
                spinnerCategoria.getSelectedItemPosition() != 0 && // Verificar que se haya seleccionado una categoría
                !editTextLinkFoto.getText().toString().isEmpty();
    }

    // Método para volver a la pantalla de perfil
    private void volverAPerfil() {
        // Obtener el FragmentManager
        FragmentManager fragmentManager = getParentFragmentManager();

        // Reemplazar el fragmento actual con el fragmento correspondiente
        PerfilFragment perfilFragment = new PerfilFragment();
        FragmentTransaction transaction = fragmentManager. beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_drawer, perfilFragment);
        transaction.addToBackStack(null); // Para permitir volver al fragmento anterior al presionar el botón "Volver"
        transaction.commit();
    }

}