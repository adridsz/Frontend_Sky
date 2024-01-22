package com.example.sky.ui.perfil;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sky.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PerfilFragment extends Fragment {
    private Context context;
    private RequestQueue requestQueue;
    private TextView carpetas;
    private TextView imagenes;
    private ImageView edit_carpetas;
    private ImageView edit_imagenes;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        context = requireActivity(); // Usamos requireActivity() en lugar de this para obtener el contexto del fragmento.
        requestQueue = Volley.newRequestQueue(requireContext());
        carpetas = view.findViewById(R.id.cartel_carpetas);
        imagenes = view.findViewById(R.id.cartel_imagenes);
        recyclerView = view.findViewById(R.id.recycler_perfil);
        edit_carpetas = view.findViewById(R.id.icono_modificar_carpetas);
        edit_imagenes = view.findViewById(R.id.icono_modificar_imagenes);

        //establecer clics en los textviews
        imagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecycler(1);
            }
        });

        carpetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecycler(2);
            }
        });

        //establecer clicls en los botones de editar
        edit_carpetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Has clicado en editar carpetas", Toast.LENGTH_LONG).show();
                mostrarPopUp(1);
            }
        });

        edit_imagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPopUp(2);
            }
        });

        return view;
    }

    private void showRecycler(int opc) {
        switch (opc) {
            case 1:
                JsonArrayRequest request1 = new JsonArrayRequest(
                        Request.Method.GET,
                        "https://raw.githubusercontent.com/tgcyn/DI/main/recursos/catalog.json",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                List<HashMap<String, String>> todasLasFotos = new ArrayList<>();
                                for (int i = 0; i < response.length(); i += 2) {
                                    try {
                                        JSONObject imagen1 = response.getJSONObject(i);
                                        ImagenesData data1 = new ImagenesData(imagen1);
                                        HashMap<String, String> item1 = new HashMap<>();
                                        item1.put("clave1", data1.getImageUrl());
                                        todasLasFotos.add(item1);

                                        if (i + 1 < response.length()) {
                                            JSONObject imagen2 = response.getJSONObject(i + 1);
                                            ImagenesData data2 = new ImagenesData(imagen2);
                                            HashMap<String, String> item2 = new HashMap<>();
                                            item2.put("clave1", data2.getImageUrl());
                                            todasLasFotos.add(item2);
                                        }
                                    } catch (JSONException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            /*  ImagenesAdapter adapter = new ImagenesAdapter(todasLasFotos, requireActivity());
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
                            */
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
                requestQueue.add(request1);
                break;
            case 2:
                JsonArrayRequest request2 = new JsonArrayRequest(
                        Request.Method.GET,
                        "https://raw.githubusercontent.com/tgcyn/DI/main/recursos/catalog.json",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                List<CarpetasData> todasLasCarpetas = new ArrayList<>();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject carpeta = response.getJSONObject(i);
                                        CarpetasData data = new CarpetasData(carpeta);
                                        todasLasCarpetas.add(data);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                CarpetasAdapter adapter = new CarpetasAdapter(todasLasCarpetas, requireActivity());
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
                requestQueue.add(request2);
                break;
        }
    }

    private void mostrarPopUp(int opc) {
        switch (opc) {
            case 1:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setMessage("Editar carpetas");

                //botón crear carpeta
                builder.setPositiveButton("Crear carpeta", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Has clicado en crear carpeta", Toast.LENGTH_LONG).show();
                    }
                });

                //botón eliminar carpeta
                builder.setNegativeButton("Eliminar carpetas", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Has clicado en elimimar carpeta", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
                break;

            case 2:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(requireContext());
                builder2.setMessage("Editar imágenes");

                //botón subir foto
                builder2.setPositiveButton("Subir foto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Has clicado en subir foto", Toast.LENGTH_LONG).show();
                    }
                });

                //botón eliminar foto
                builder2.setNegativeButton("Eliminar foto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Has clicado en eliminar foto", Toast.LENGTH_LONG).show();
                    }
                });
                builder2.show();
                break;
        }
    }
}