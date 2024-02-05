package com.example.sky.login;

// Importaciones necesarias para la clase
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sky.Drawer;
import com.example.sky.R;
import com.example.sky.ui.inicio.InicioFragment;
import com.google.firebase.auth.FirebaseAuth;

// Definición de la clase LoginActivity que extiende de AppCompatActivity
public class LoginActivity extends AppCompatActivity {

    // Declaración de los campos de texto para el nombre y la contraseña
    private EditText Name;
    private EditText Password;

    // Declaración de la instancia de FirebaseAuth
    private FirebaseAuth mAuth;

    private boolean error = true;

    // Método onCreate que se ejecuta al crear la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llamada al método onCreate de la clase padre
        setContentView(R.layout.activity_login); // Establece el diseño de la actividad

        mAuth = FirebaseAuth.getInstance(); // Inicializa mAuth con la instancia de FirebaseAuth

        // Inicializa los campos de texto con las vistas correspondientes del diseño
        Name = findViewById(R.id.Correo_electrónico);
        Password = findViewById(R.id.Password);

        // Inicializa los botones con las vistas correspondientes del diseño
        Button buttonLogin = findViewById(R.id.buttonnLoggin);
        Button buttonRegister = findViewById(R.id.buttonnReggister);

        // Establece el oyente del botón de registro para iniciar la actividad de registro al hacer clic
        buttonRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        // Establece el oyente del botón de inicio de sesión para llamar al método iniciarSesion al hacer clic
        buttonLogin.setOnClickListener(v -> iniciarSesion());
    }

    // Método para iniciar sesión
    private void iniciarSesion() {
        // Obtiene el correo electrónico y la contraseña de los campos de texto
        String email = Name.getText().toString().trim();
        String password = Password.getText().toString().trim();

        // Intenta iniciar sesión con el correo electrónico y la contraseña
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    // Si la tarea es exitosa, inicia la actividad InicioFragment, muestra un mensaje de bienvenida y termina la actividad actual
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, Drawer.class));
                    error = false;
                    finish();
                })
                .addOnFailureListener(this, e -> {
                    if (error == true) {
                        // Si la tarea falla con una excepción, imprime la excepción
                        e.printStackTrace();
                        Toast.makeText(this, "Error al iniciar sesión" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
