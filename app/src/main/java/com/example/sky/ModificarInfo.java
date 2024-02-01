package com.example.sky;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModificarInfo extends AppCompatActivity {
    private Button botConf;
    private FloatingActionButton botModIma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_info);

        //Obtenemos las vistas de los botones
        botConf = findViewById(R.id.botConfirm);
        botModIma = findViewById(R.id.lapiz);


        //PROVISIONAL: al presionar el botón confirmar, la actividad vuelve para atrás sin más,
        //sin aplicar nada
        botConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Al pulsar en el botón de modificar imagen (el lápiz), le decimos que
        //muestre un pop up
        botModIma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPopUp();
            }
        });

    }

    //En este método creamos y definimos el pop up
    private void mostrarPopUp() {
        //Creamos el AlertDialog (pop up)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Le decimos el mensaje que mostrar
        builder.setMessage("Introduce el link para la nueva foto de perfil");

        //Esto permite que el usuario introduzca el link de la nueva foto de perfil,
        //creando un EditText
        EditText editText = new EditText(this);
        //y diciendo que se muestre
        builder.setView(editText);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //Si pulsamos en Aceptar, de momento, cerramos el pop up sin más
                }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Si pulsamos en Cancelar se cierra el pop up sin más
            }
        });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
