package com.example.sky.ui.inicio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sky.Drawer;
import com.example.sky.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class InicioFragment extends Fragment {

    private Context context;
    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private Button categAnimal;
    private Button categPaisajismo;
    private Button categComida;
    private Button categOcio;
    private Button categHobby;
    private Activity activity;
    private ImagenRecyclerViewAdaptar imagenAdapter;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        categAnimal = view.findViewById(R.id.categAnimal);
        categPaisajismo = view.findViewById(R.id.categPaisajismo);
        categComida = view.findViewById(R.id.categComida);
        categOcio = view.findViewById(R.id.categOcio);
        categHobby = view.findViewById(R.id.categHobby);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = getContext();
        requestQueue = Volley.newRequestQueue(context);

        showRecyclerView(view);

        // Crea el adaptador vacío (puedes inicializarlo con datos vacíos si lo deseas)
        imagenAdapter = new ImagenRecyclerViewAdaptar(new ArrayList<>(), activity);

        recyclerView = view.findViewById(R.id.recycler_view);
        // Configura el RecyclerView con un LinearLayoutManager
        //LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        //recyclerView.setLayoutManager(layoutManager);

        // Asigna el adaptador al RecyclerView
        recyclerView.setAdapter(imagenAdapter);

        buttonRegister.setOnClickListener(v -> registrarUsuario());
        buttonRegister.setOnClickListener(v -> registrarUsuario());
    }


    private void showRecyclerView(View view ) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/adridsz/Frontend_Sky/main/app/imagenesInicio.json",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<ImagenData> allTheBooks = new ArrayList<>();
                        for (int i=0; i< response.length(); i++) {
                            try{
                                JSONObject libro = response.getJSONObject(i);
                                ImagenData data = new ImagenData(libro);
                                allTheBooks.add(data);
                            }catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ImagenRecyclerViewAdaptar adapter = new ImagenRecyclerViewAdaptar(allTheBooks, getActivity());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        this.requestQueue.add(request);
    }


    private void viewRecyclerView() {

        // Crea el adaptador vacío (puedes inicializarlo con datos vacíos si lo deseas)
        ImagenRecyclerViewAdaptar adapter = new ImagenRecyclerViewAdaptar(new ArrayList<>(), activity);

        // Configura el RecyclerView con un LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);

        // Asigna el adaptador al RecyclerView
        recyclerView.setAdapter(adapter);
    }
}