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

public class OtrosPerfilesFragment extends Fragment {

    private Context context;
    private boolean mostrarImagenes = true;
    private RecyclerView recyclerViewImagenes;

    // Método llamado cuando se crea la vista del fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento_otros_perfiles.xml
        View view = inflater.inflate(R.layout.fragment_otros_perfiles, container, false);

        // Inicializar el contexto
        context = requireContext();

        // Obtén referencias a las vistas
        ImageView fotoPerfil = view.findViewById(R.id.fotoPerfil);
        TextView nombreUsuario = view.findViewById(R.id.nombreUsuario);
        TextView descripcionUsuario = view.findViewById(R.id.descripcionUsuario);

        Button btnImagenes = view.findViewById(R.id.btnImagenes);
        Button btnCarpetas = view.findViewById(R.id.btnCarpetas);

        recyclerViewImagenes = (RecyclerView) view.findViewById(R.id.recyclerViewImagenes);

        ImageView ImageViewCarpeta = view.findViewById(R.id.ImageViewCarpeta);

        // Maneja los clics en los botones de imágenes y carpetas
        btnImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarImagenes = true;
                ImageViewCarpeta.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Estás en imágenes", Toast.LENGTH_SHORT).show();
                recyclerViewImagenes.setVisibility(View.VISIBLE);
                peticionImagenes();
            }
        });

        btnCarpetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarImagenes = false;
                recyclerViewImagenes.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Estás en carpetas", Toast.LENGTH_SHORT).show();
                ImageViewCarpeta.setVisibility(View.VISIBLE);
            }
        });

        ImageViewCarpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el FragmentManager
                FragmentManager fragmentManager = getParentFragmentManager();

                // Reemplazar el fragmento actual con el fragmento CarpetaFragment
                CarpetaFragment carpetaFragment = new CarpetaFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.nav_host_fragment_content_drawer, carpetaFragment);
                transaction.addToBackStack(null); // Para permitir volver al fragmento anterior al presionar el botón "Volver"
                transaction.commit();
            }
        });

        // Hacer la petición de imágenes
        peticionImagenes();

        return view; // Devolver la vista inflada
    }

    // Método para hacer la petición de imágenes
    private void peticionImagenes() {
        List<Foto> listaFoto = new ArrayList<>();

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://raw.githubusercontent.com/adridsz/Frontend_Sky/bet/app/imagenesOtrosPerfiles.json",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Iterar sobre la respuesta JSON para obtener objetos de imagen
                            for (int i=0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Foto foto = new Foto(jsonObject);
                                listaFoto.add(foto);
                            }
                            // Configurar el adaptador para el RecyclerView y mostrar las imágenes
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
                        // Manejar errores de la petición
                        Toast.makeText(getActivity(), "Error" + error, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Agregar la solicitud a la cola de Volley
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);

    }
}
