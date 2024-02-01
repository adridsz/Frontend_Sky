package com.example.sky.login;

// Importaciones necesarias para la clase
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sky.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

// Definición de la clase RegisterActivity que extiende de AppCompatActivity
public class RegisterActivity extends AppCompatActivity {

    // Declaración de los campos de texto para el nombre, correo electrónico y contraseña
    private EditText editTextNombre, editTextEmail, editTextPassword;

    // Declaración de la instancia de FirebaseAuth
    private FirebaseAuth mAuth;

    // Método onCreate que se ejecuta al crear la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Llamada al método onCreate de la clase padre
        setContentView(R.layout.activity_register); // Establece el diseño de la actividad

        // Verifica si la aplicación Firebase ya está inicializada, si no, la inicializa
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this);
        }

        mAuth = FirebaseAuth.getInstance(); // Inicializa mAuth con la instancia de FirebaseAuth

        // Inicializa los campos de texto para que se adjunten a su apartado del xml
        editTextNombre = findViewById(R.id.NombreUsuario);
        editTextEmail = findViewById(R.id.Correo_electrónico);
        editTextPassword = findViewById(R.id.Contraseña);

        // Inicializamos el boton para que se adjunte a su apartado del xml
        Button buttonRegister = findViewById(R.id.buttonnRegister);

        // Inicializamos el boton para que se adjunte a su apartado del xml
        Button buttonVolver = findViewById(R.id.buttonnVolver);

        // Establece el oyente del botón de registro para llamar al método registrarUsuario al hacer clic
        buttonRegister.setOnClickListener(v -> registrarUsuario());
        buttonVolver.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }

    // Método para registrar usuario
    private void registrarUsuario() {
        // Obtiene el nombre, correo electrónico y contraseña de los campos de texto
        String nombre = editTextNombre.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Verifica si el nombre, correo electrónico y contraseña son válidos
        if (TextUtils.isEmpty(nombre)) {
            editTextNombre.setError("Ingresa el nombre de Usuario");
            return;
        }

        if (TextUtils.isEmpty(email)){
            editTextEmail.setError("Ingresa el correo electrónico");
            return;
        }

        if (TextUtils.isEmpty(password)  || password.length() < 6) {
            editTextPassword.setError("Ingresa la contraseña");
            return;
        }

        // Intentamos crear un usuario con el correo electrónico y la contraseña
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    // Si todo sale bien, obtiene el usuario actual y guarda los datos adicionales en la base de datos
                    //if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Usuario nuevoUsuario = new Usuario(nombre, email);
                        FirebaseDatabase.getInstance().getReference("usuarios")
                                .child(user.getUid())
                                .setValue(nuevoUsuario)
                                .addOnCompleteListener(taskDb -> {
                                    //aquí tambien podríamos añadir un if (task.isSucesssful()) para que no se caiga la app. En este caso lo quitamos
                                    // Si la tarea de la base de datos es exitosa, muestra un mensaje de éxito y inicia la actividad LoginActivity
                                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                })
                                .addOnFailureListener(this, e -> {
                                    // Si el programa nos falla con una excepción, la imprime y mostraremos un mensaje de error
                                    e.printStackTrace();
                                    Toast.makeText(this, "Error al guardar datos"+e.getMessage(),  Toast.LENGTH_SHORT).show();
                                });
                    //} else {
                        // Si este falla, mostramos un mensaje de error.
                        Log.e("TagError", task.getException().getMessage());
                        Toast.makeText(RegisterActivity.this, "Registro fallido: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                });//En caso de borrar esta llave hay que descomentar la de abajo y crear un if para que no se caiga la app
        }

        //});

    private class Usuario {
        public String nombre;
        public String email;

    // Constructor de la clase Usuario
        public Usuario(String nombre, String email) {
            this.nombre = nombre;
            this.email = email;
        }
    }
}