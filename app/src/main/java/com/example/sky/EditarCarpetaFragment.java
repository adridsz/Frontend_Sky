package com.example.sky;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class EditarCarpetaFragment extends Fragment {
    private Button btnEditarCarpeta;
    private Context context;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_carpeta, container, false);
    }
    public static EditarCarpetaFragment newInstance(String nombreCarpeta) {
        EditarCarpetaFragment fragment = new EditarCarpetaFragment();
        Bundle args = new Bundle();
        args.putString("nombreCarpeta", nombreCarpeta);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Obtenemos nuevamente el contexto de la aplicación
        String nombreCarpeta = getArguments().getString("nombreCarpeta");
        context = getContext();
        // Inicializamos la cola de solicitudes de Volley
        requestQueue = Volley.newRequestQueue(context);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(nombreCarpeta);
        btnEditarCarpeta = view.findViewById(R.id.btnEditarCarpeta);
        btnEditarCarpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPopUp();
            }
        });
        verRecyclerView(getView(), nombreCarpeta);
    }

    private void mostrarPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("Editar carpeta");

        builder.setPositiveButton("Eliminar imagenes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //Aqui agregar menu de eliminar imagenes
            }
        });

        builder.setNegativeButton("Eliminar carpeta", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                confirmarEliminacionCarpeta();
            }
        });
        builder.show();
    }

    private void confirmarEliminacionCarpeta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("¿Estás seguro de que quieres eliminar la carpeta?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                // Aqui se agregaria el codigo para eliminar carpeta, al ser una app de ejemplo y tener un json como plantilla esto no funciona
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void verRecyclerView(View view, String nombre) {
        // Obtenemos una referencia al RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewEditarCarpeta);
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/adridsz/Frontend_Sky/main/app/"+nombre+".json",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<ImagenesData> imagenes = new ArrayList<>();
                        for (int i = 0; i < response.length(); i += 2) {
                            try {
                                JSONObject imagen1 = response.getJSONObject(i);
                                String imagenurl1 = imagen1.getString("image_url");

                                String imagenurl2 = null;
                                if (i+1 < response.length()){
                                    JSONObject imagen2 = response.getJSONObject(i+1);
                                    imagenurl2 = imagen2.getString("image_url");
                                }

                                ImagenesData data = new ImagenesData(imagenurl1, imagenurl2);
                                imagenes.add(data);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        ImagenesAdapter adapter = new ImagenesAdapter(imagenes, requireActivity());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(request);
    }
}