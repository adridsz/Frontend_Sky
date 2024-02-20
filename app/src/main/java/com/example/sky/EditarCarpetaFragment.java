package com.example.sky;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EditarCarpetaFragment extends Fragment {
    private Button btnEditarCarpeta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_carpeta, container, false);
    }
    public static EditarCarpetaFragment newInstance(String nombreCarpeta) {
        EditarCarpetaFragment fragment = new EditarCarpetaFragment();
        Bundle args = new Bundle();
        args.putString("nombreCarpeta", nombreCarpeta);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.textView);
        if (getArguments() != null) {
            String nombreCarpeta = getArguments().getString("nombreCarpeta");
            textView.setText(nombreCarpeta);
        }
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
                confirmarEliminacionCarpeta();
            }
        });
        builder.show();
    }

    private void confirmarEliminacionCarpeta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("¿Estás seguro de que quieres eliminar la carpeta?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                // Aqui se agregaria el codigo para eliminar carpeta, al ser una app de ejemplo y tener un json como plantilla esto no funciona
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}