package com.example.sky;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class CarpetaFragment extends Fragment {

    private Context context;
    private boolean mostrarImagenes = true;
    private RecyclerView recyclerViewImagenes;

    @Override
    // Método llamado cuando se crea la vista del fragmento
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento_carpeta.xml
        View view = inflater.inflate(R.layout.fragment_carpeta, container, false);

        context = requireContext();

        // Obtén referencias a las vistas
        Button botonVolver = view.findViewById(R.id.botonVolver);
        recyclerViewImagenes = (RecyclerView) view.findViewById(R.id.recyclerViewImagenes);

        // Configuración inicial
        mostrarImagenes = true;
        recyclerViewImagenes.setVisibility(View.VISIBLE);
        peticionImagenes();

        // Manejar clics en el botón Volver
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarImagenes = false;
                recyclerViewImagenes.setVisibility(View.GONE);
                // Obtener el FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Reemplazar el fragmento actual con el fragmento FragPerfil
                OtrosPerfilesFragment otrosPerfilesFragment = new OtrosPerfilesFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.otrosPerfilesFragment, otrosPerfilesFragment);
                transaction.addToBackStack(null); // Para permitir volver al fragmento anterior al presionar el botón "Volver"
                transaction.commit();
            }
        });

        return view; // Devolver la vista inflada
    }

    // Método para realizar la solicitud de imágenes
    private void peticionImagenes() {
        List<Foto> listaFoto = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/adridsz/Frontend_Sky/bet/app/imagenesCarpetaOtrosPerfiles.json",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Iterar sobre la respuesta JSON
                            for (int i=0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Foto foto = new Foto(jsonObject);
                                listaFoto.add(foto);
                            }
                            // Configurar el adaptador y el RecyclerView
                            FotosAdapter adapter = new FotosAdapter(listaFoto);
                            recyclerViewImagenes.setAdapter(adapter);
                            recyclerViewImagenes.setLayoutManager(new LinearLayoutManager(context));
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Error" + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Agregar la solicitud a la cola de Volley
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);

    }
}