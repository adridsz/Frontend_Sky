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

public class SubirFotoFragment extends Fragment {

    public SubirFotoFragment() {
        // Constructor vacío requerido por la API de fragmentos
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subir_foto, container, false);

        // Configurar el Spinner con las categorías
        Spinner spinnerGenero = rootView.findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.categoria, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenero.setAdapter(adapter);

        Button btnConfirmar = rootView.findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarCampos()) {
                    // Guardar la información y volver a la pantalla de "Main Activity"
                    Toast.makeText(requireContext(), "Foto subida con éxito", Toast.LENGTH_SHORT).show();
                    volverAMainActivity();
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
                // Volver a la pantalla de "Main Activity"
                volverAMainActivity();
            }
        });

        return rootView;
    }

    private boolean validarCampos() {
        // Validar campos antes de confirmar
        EditText editTextTituloFoto = requireView().findViewById(R.id.editTextTituloFoto);
        Spinner spinnerCategoria = requireView().findViewById(R.id.spinnerCategoria);
        EditText editTextLinkFoto = requireView().findViewById(R.id.editTextLinkFoto);

        return !editTextTituloFoto.getText().toString().isEmpty() &&
                spinnerCategoria.getSelectedItemPosition() != 0 && // Verificar que se haya seleccionado una categoría
                !editTextLinkFoto.getText().toString().isEmpty();
    }

    private void volverAMainActivity() {
        // Volver a la actividad MainActivity
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}