package com.example.sky.ui.perfil.EliminarCarpeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sky.R;
import com.example.sky.ui.perfil.Carpetas.CarpetasAdapter;
import com.example.sky.ui.perfil.Carpetas.CarpetasData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EliminarCarpetas extends AppCompatActivity {
    private Context context;
    private RequestQueue requestQueue;
    private TextView confirmar;
    private TextView cancelar;
    private String nombreCarpeta;
    private int idImagen = R.drawable.icono_carpeta;
    private List<CarpetasData> todasLasCarpetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_carpetas);

        context = this;

        cancelar = findViewById(R.id.cancelar);
        confirmar = findViewById(R.id.confirmar);


        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "CLiacaste", Toast.LENGTH_LONG).show();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //esto hace que vuelva a la pantalla anterior
            }
        });

        showRecyclerView();

    }

    private void showRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_eliminar);
        Activity activity = this;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/tgcyn/DI/main/recursos/catalog.json",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        todasLasCarpetas = new ArrayList<>();

                        for (int i=0; i<response.length(); i++) {
                            try{
                                JSONObject carpeta = response.getJSONObject(i);
                                nombreCarpeta = carpeta.getString("name");
                                CarpetasData data = new CarpetasData(nombreCarpeta, idImagen);
                                todasLasCarpetas.add(data);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        ECAdapter adapter = new ECAdapter(todasLasCarpetas, activity);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Error: " + error, Toast.LENGTH_LONG).show();
                    }
                }
        );
        this.requestQueue.add(request);
    }
}