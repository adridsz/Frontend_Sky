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

        //Para finalizar, con un .finish(), rollo un botón que te vuelva para atrás

        botConf = findViewById(R.id.botConfirm);
        botModIma = findViewById(R.id.lapiz);


        //PROVISIONAL: que vuelva para atrás

        botConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        botModIma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPopUp();
            }
        });

    }

    private void mostrarPopUp() {
        //Creamos el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Introduce el link para la nueva foto de perfil");

        //Esto permite que el usuario introduzca el link de la nueva foto de perfil
        EditText editText = new EditText(this);
        builder.setView(editText);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //de momento, cerramos el pop up sin más
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
