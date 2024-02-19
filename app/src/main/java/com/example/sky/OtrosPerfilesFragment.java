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

public class OtrosPerfilesFragment extends Fragment {

    private Context context;
    private boolean mostrarImagenes = true;
    private RecyclerView recyclerViewImagenes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño del fragmento
        View view = inflater.inflate(R.layout.fragment_otros_perfiles, container, false);

        context = requireContext();

        // Obtén referencias a las vistas
        ImageView fotoPerfil = view.findViewById(R.id.fotoPerfil);

        TextView nombreUsuario = view.findViewById(R.id.nombreUsuario);
        TextView descripcionUsuario = view.findViewById(R.id.descripcionUsuario);

        Button btnImagenes = view.findViewById(R.id.btnImagenes);
        Button btnCarpetas = view.findViewById(R.id.btnCarpetas);

        recyclerViewImagenes = (RecyclerView) view.findViewById(R.id.recyclerViewImagenes);

        // Configura la imagen de perfil y el nombre de usuario
        fotoPerfil.setImageResource(R.drawable.ic_launcher_foreground); // Reemplaza con la lógica para cargar la foto
        nombreUsuario.setText("Nombre de usuario"); // Reemplaza con la lógica para cargar el nombre

        // Configura la descripción del usuario
        descripcionUsuario.setText("Descripción del usuario...");

        // Maneja los clics en los botones de imágenes y carpetas
        btnImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarImagenes = true;
                recyclerViewImagenes.setVisibility(View.VISIBLE);
                showToast("Estás en imágenes");
                peticionImagenes();
            }
        });

        btnCarpetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarImagenes = false;
                recyclerViewImagenes.setVisibility(View.GONE);
                showToast("Estás en carpetas");
                // Agregar lógica para cargar carpetas del usuario
            }
        });

        peticionImagenes();

        return view;
    }

    // Método para mostrar Toast
    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void peticionImagenes() {
        List<Foto> listaFoto = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                null,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i=0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Foto foto = new Foto(jsonObject);
                                listaFoto.add(foto);
                            }
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
