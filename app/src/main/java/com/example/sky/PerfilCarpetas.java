package com.example.sky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PerfilCarpetas extends AppCompatActivity {
    private Context context;
    private RequestQueue requestQueue;
    private TextView carpetas;
    private TextView imagenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        context = this;
        requestQueue = Volley.newRequestQueue(this);
        carpetas = findViewById(R.id.cartel_carpetas);
        imagenes = findViewById(R.id.cartel_imagenes);

        //esta hace q al pulsar en imagenes, se visualice el recycler de Imagenes
        imagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecycler(1);
            }
        });

        //esto hace q al pulsar en carpetas se visualice el recycler de Carpteas
        carpetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecycler(2);
            }
        });

    }

    private void showRecycler(int opc) {
        RecyclerView recyclerView = findViewById(R.id.recycler_perfil);
        Activity activity = this;

        context = this;

        switch (opc){
            case 1:
                //Toast.makeText(this, "Prueba si el switch funciona", Toast.LENGTH_LONG).show();
                JsonArrayRequest request1 = new JsonArrayRequest(
                        Request.Method.GET,
                        "https://raw.githubusercontent.com/tgcyn/DI/main/recursos/catalog.json", //cambiar cnd este listo el json
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                HashMap<String, String> imagenes = new HashMap<>();
                                List<HashMap<String, String>> todasLasFotos = new ArrayList<>();
                                /*int i=0;
                                for (int i=0; i<response.length(); i++) {
                                    try {
                                        imagenes.put()
                                        JSONObject imagen = response.getJSONObject(i);
                                        ImagenesData data = new ImagenesData(imagen);
                                        todasLasFotos.add(data);
                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                while (i<=response.length()) {
                                    try {

                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }


                                }
                                ImagenesAdapter adapter = new ImagenesAdapter(todasLasFotos, activity);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(activity));*/

                                for (int i = 0; i < response.length(); i += 2) {
                                    // Obtener datos para el primer elemento en la celda
                                    try {
                                        JSONObject imagen1 = response.getJSONObject(i);
                                        ImagenesData data1 = new ImagenesData(imagen1);
                                        HashMap<String, String> item1 = new HashMap<>();
                                        item1.put("clave1", data1.getImageUrl());
                                        todasLasFotos.add(item1);
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }

                                    // Verificar si hay un segundo elemento en la celda
                                    if (i + 1 < response.length()) {
                                        // Obtener datos para el segundo elemento en la celda
                                        try {
                                            JSONObject imagen2 = response.getJSONObject(i + 1);
                                            ImagenesData data2 = new ImagenesData(imagen2);
                                            HashMap<String, String> item2 = new HashMap<>();
                                            item2.put("clave1", data2.getImageUrl());
                                            todasLasFotos.add(item2);
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                this.requestQueue.add(request1);
                break;
            case 2:
                JsonArrayRequest request2 = new JsonArrayRequest(
                        Request.Method.GET,
                        "https://raw.githubusercontent.com/tgcyn/DI/main/recursos/catalog.json", //cambiarlo cnd este el json listo
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                List<CarpetasData> todasLasCarpetas = new ArrayList<>();
                                for (int i=0; i<response.length(); i++) {
                                    try {
                                        JSONObject carpeta = response.getJSONObject(i);
                                        CarpetasData data = new CarpetasData(carpeta);
                                        todasLasCarpetas.add(data);
                                    }catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                CarpetasAdapter adapter = new CarpetasAdapter(todasLasCarpetas, activity);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                this.requestQueue.add(request2);
                break;
        }
    }
}