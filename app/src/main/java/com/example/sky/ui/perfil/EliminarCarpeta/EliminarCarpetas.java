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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
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
    private List<ECData> todasLasCarpetas;
    private ECAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_carpetas);

        context = this.getApplicationContext();
        requestQueue = Volley.newRequestQueue(context);

        showRecyclerView();

        cancelar = (TextView) findViewById(R.id.cancelar);
        confirmar = (TextView) findViewById(R.id.confirmar);

        //NO ENTRA EN ESTE METODO
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Probar si entra", Toast.LENGTH_LONG).show();
                mostrarPopUp(); //si comento esta linea funciona, con ella no
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Probar si entra", Toast.LENGTH_LONG).show();

                setResult(Activity.RESULT_CANCELED); //esto devuelve al codigo de PerfilFragment q el usuario cancelo la operacion
                finish(); //esto hace que vuelva a la pantalla anterior
            }
        });


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
                                ECData data = new ECData(nombreCarpeta);
                                todasLasCarpetas.add(data);
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        adapter = new ECAdapter(todasLasCarpetas, activity);
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

    private void mostrarPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Seguro que quiere eliminar");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /*List<ECData> carpetasSeleccionadas = adapter.getSelected();

                // Aquí implementa la lógica para eliminar las carpetas seleccionadas
                for (ECData carpeta : carpetasSeleccionadas) {
                    todasLasCarpetas.remove(carpeta);
                }

                // Notifica al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();*/

                adapter.eliminarCarpetas();


                setResult(Activity.RESULT_OK); //esto devuelve a PerfilFragment q la operacion fue exitosa
                finish(); //comentando esta linea se puede ver q si se dejan de visualizar las seleccionadas
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }
}