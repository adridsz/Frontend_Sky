package com.example.sky;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sky.ui.perfil.Imagenes.ImagenesAdapter;
import com.example.sky.ui.perfil.Imagenes.ImagenesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EliminarImagenCarpeta extends AppCompatActivity {
    private Button btnConfirmarEliminar;
    private Activity activity;
    private String nombreCarpeta;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_imagen_carpeta);
        Intent intent = getIntent();
        nombreCarpeta = intent.getStringExtra("nombreCarpeta");
        // Inicializamos la cola de solicitudes de Volley
        requestQueue = Volley.newRequestQueue(this);

        // Encuentra el TextView y establece su texto al valor de nombreCarpeta
        TextView textoCarpeta = findViewById(R.id.TextoCarpeta);
        textoCarpeta.setText(nombreCarpeta);

        btnConfirmarEliminar = findViewById(R.id.btnConfirmarEliminar);
        btnConfirmarEliminar.setOnClickListener(v -> {
            // Elimina las imagenes de la carpeta
            eliminarImagenes();
        });
        verRecyclerView();
    }

    private void eliminarImagenes() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de que quieres eliminar las imágenes?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Aquí iria el codigo que permitira eliminar las imagenes de la carpeta, al no haber back, no funciona
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Si el usuario cancela la acción, simplemente cierras el diálogo
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    private void verRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEliminarImagenCarpeta);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/adridsz/Frontend_Sky/main/app/"+nombreCarpeta+".json",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<EliminarImagenCarpetaData> imagenes = new ArrayList<>();
                        for (int i = 0; i < response.length(); i += 2) {
                            try {
                                JSONObject imagen1 = response.getJSONObject(i);
                                String imagenurl1 = imagen1.getString("image_url");

                                String imagenurl2 = null;
                                if (i+1 < response.length()){
                                    JSONObject imagen2 = response.getJSONObject(i+1);
                                    imagenurl2 = imagen2.getString("image_url");
                                }

                                EliminarImagenCarpetaData data = new EliminarImagenCarpetaData(imagenurl1, imagenurl2);
                                imagenes.add(data);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        EliminarImagenCarpetaAdapter adapter = new EliminarImagenCarpetaAdapter(imagenes, EliminarImagenCarpeta.this);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(EliminarImagenCarpeta.this));
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EliminarImagenCarpeta.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}