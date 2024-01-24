package com.example.sky;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EditarCarpetaFragment extends Fragment {
    private Button btnEditarCarpeta;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_carpeta, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnEditarCarpeta = view.findViewById(R.id.btnEditarCarpeta);
        btnEditarCarpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPopUp();
            }
        });
    }

    private void mostrarPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("Editar carpeta");

        builder.setPositiveButton("Eliminar imagenes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Aqui agregar menu de eliminar imagenes
            }
        });

        builder.setNegativeButton("Eliminar carpeta", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}