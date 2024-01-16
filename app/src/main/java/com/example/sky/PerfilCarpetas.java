package com.example.sky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import java.util.List;

public class PerfilCarpetas extends AppCompatActivity {
    private Context context;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        context = this;
        requestQueue = Volley.newRequestQueue(this);

        showRecyclerView();
    }

    private void showRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_carpetas);
        Activity activity = this;

        context = this;

        JsonArrayRequest request = new JsonArrayRequest(
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
        this.requestQueue.add(request);
    }
}