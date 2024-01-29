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

        //SPINNER
        //Configuramos un Spinner que muestra la lista de temas. Utilizamos
        //un adaptador de matriz 'ArrayAdapter' para asociar un array de Strings 'R.array.array_temas' con el Spinner
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
                //Cuando seleccionamos un tema en el Spinner, se llama al siguiente método
                //con el tema seleccionado como argumento
                mostrarAlertDialog(temas[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Si no seleccionamos nada, no lo usamos
            }
        });

        //Botón cancelar
        botCancelar = findViewById(R.id.botCanc);
        botCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Al pulsar en Cancelar, iniciamos la actividad del Drawer (como volver atrás)
                Intent intent = new Intent(ModificarTemas.this, Drawer.class);
                startActivity(intent);
            }
        });

    }


    //Método que muestra el pop up
    private void mostrarAlertDialog(String temaSeleccionado) {
        //Creamos el AlertDialog (pop up)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Aquí le decimos que si NO selecciona la opción de las barritas, se muestre el siguiente pop up
        //(Es decir, si seleccionamos las barritas no hace nada
        if (!temaSeleccionado.equals("------")) {
            builder.setMessage("Has seleccionado: " + temaSeleccionado + "\nPara aplicarlo es necesario reiniciar la aplicación");
            builder.setPositiveButton("Reiniciar Aplicación", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Aquí invocamos el método que reinicia la app
                    reiniciarApp();
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Al pulsar en Cancelar se cierra el pop up sin más
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    //Con este método reiniciamos la aplicación.
    private void reiniciarApp() {
        //Se crea un nuevo intent que abre la actividad Drawer.class (que abre directamente el Inicio)
        Intent intent = new Intent(ModificarTemas.this, Drawer.class);
        //finalizamos la actividad actual
        finish();
        //reiniciamos la nueva actividad
        startActivity(intent);
    }


}