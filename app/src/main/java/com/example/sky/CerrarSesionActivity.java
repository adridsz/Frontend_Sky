package com.example.sky;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CerrarSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);

        TextView tvMessage = findViewById(R.id.tvMessage);
        Button btnConfirmar = findViewById(R.id.btnConfirmar);
        Button btnCancelar = findViewById(R.id.btnCancelar);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirigir a la pantalla de inicio de sesión.
                finish(); // Cierra la actividad actual
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish();
            }
        });
    }
}
