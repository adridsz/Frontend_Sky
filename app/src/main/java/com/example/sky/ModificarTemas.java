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
import android.widget.Toast;

public class ModificarTemas extends AppCompatActivity {
    private Button botCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_temas);

        //Spinner
        Spinner spinnerTemas = findViewById(R.id.spinner_temas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_temas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTemas.setAdapter(adapter);

        //Listener para el spinner
        String[] temas = getResources().getStringArray(R.array.array_temas);
        spinnerTemas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlertDialog(temas[position]);
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
                Intent intent = new Intent(ModificarTemas.this, Drawer.class);
                startActivity(intent);
            }
        });

    }

    private void mostrarAlertDialog(String temaSeleccionado) {
        //Creamos el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (!temaSeleccionado.equals("------")) {
            builder.setMessage("Has seleccionado: " + temaSeleccionado + "\nPara aplicarlo es necesario reiniciar la aplicación");
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
        Intent intent = new Intent(ModificarTemas.this, Drawer.class);
        finish();
        startActivity(intent);
    }


}