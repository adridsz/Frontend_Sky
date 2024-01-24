package com.example.sky;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ModificarFuentes extends AppCompatActivity {
    private Button botCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_fuentes);

        //Spinner
        Spinner spinnerFuentes = findViewById(R.id.spinner_fuentes);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_fuentes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuentes.setAdapter(adapter);

        //Listener para el spinner
        String[] fuentes = getResources().getStringArray(R.array.array_fuentes);
        spinnerFuentes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlertDialog(fuentes[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //botón cancelar
        botCancelar = findViewById(R.id.botCanc);
        botCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModificarFuentes.this, Drawer.class);
                startActivity(intent);
            }
        });

    }

    private void mostrarAlertDialog(String fuenteSeleccionada) {
        //Creamos el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (!fuenteSeleccionada.equals("------")) {
            builder.setMessage("Has seleccionado: " + fuenteSeleccionada + "\nPara aplicarla es necesario reiniciar la aplicación");
            builder.setPositiveButton("Reiniciar Aplicación", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    reiniciarApp();
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Se cierra el pop up sin más
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void reiniciarApp() {
        Intent intent = new Intent(ModificarFuentes.this, Drawer.class);
        finish();
        startActivity(intent);
    }

}