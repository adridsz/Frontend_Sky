package com.example.sky.ui.perfil;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.EditText;
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
import com.example.sky.SubirFotoFragment;
import com.example.sky.ui.perfil.Carpetas.CarpetasAdapter;
import com.example.sky.ui.perfil.Carpetas.CarpetasData;
import com.example.sky.ui.perfil.EliminarCarpeta.ECAdapter;
import com.example.sky.ui.perfil.EliminarCarpeta.ECData;
import com.example.sky.ui.perfil.EliminarCarpeta.EliminarCarpetas;
import com.example.sky.ui.perfil.Imagenes.ImagenesAdapter;
import com.example.sky.ui.perfil.Imagenes.ImagenesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.sky.ModificarInfo;
import com.example.sky.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class PerfilFragment extends Fragment {

    private FloatingActionButton editar;
    private Context context;
    private RequestQueue requestQueue;
    private TextView carpetas;
    private TextView imagenes;
    private ImageView edit_carpetas;
    private ImageView edit_imagenes;
    private RecyclerView recyclerView;
    private int idImagen = R.drawable.icono_carpeta;
    private String nombreCarpeta;
    private CarpetasAdapter carpetasAdapter;
    private List<CarpetasData> todasLasCarpetas;
    private List<ImagenesData> todasLasFotos;
    private static final int CODIGO_PARA_ELIMINAR_CARPETAS = 1;
    private List<ECData> carpetasEliminar = new ArrayList<>();
    private List<CarpetasData> carpetasNoEliminadas = new ArrayList<>();
    private ECAdapter ecadapter;


    //esto es el constructor del fragment
    public PerfilFragment() {
        todasLasCarpetas = new ArrayList<>();
    }

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

        //pongo para q por defecto al iniciar la pantalla se visualicen las imagenes
        showRecycler(1);

        //lleno la lista con las carpetas a eliminar
        llenarList();

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

        //inicialiazo el adapter con la lista actual de carpetas
        carpetasAdapter = new CarpetasAdapter(todasLasCarpetas, getActivity());

        //inicializo ecadapter son la lista de carpetas
        ecadapter = new ECAdapter(carpetasEliminar,getActivity());


        return view;
    }

    private void showRecycler(int opc) {
        switch (opc) {
            case 1:
                JsonArrayRequest request1 = new JsonArrayRequest(
                    Request.Method.GET,
                    "https://raw.githubusercontent.com/adridsz/Frontend_Sky/281a135354d544f21434293c29d7331409dd8748/app/animales.json",
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            todasLasFotos = new ArrayList<>();
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
                                    todasLasFotos.add(data);
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            ImagenesAdapter adapter = new ImagenesAdapter(todasLasFotos, requireActivity());
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
                            todasLasCarpetas.clear(); //limpio la lista de carpetas antes de agregar otra

                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject carpeta = response.getJSONObject(i);
                                    nombreCarpeta = carpeta.getString("name");
                                    CarpetasData data = new CarpetasData(nombreCarpeta, idImagen);
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
            //este caso solo es para hacer la trampa del borrado: si añado un titulo de los q ya existen y dps elimino todos menos ese solo aparece ese
            case 3:
                JsonArrayRequest request3 = new JsonArrayRequest(
                        Request.Method.GET,
                        "https://raw.githubusercontent.com/tgcyn/DI/main/recursos/catalog.json",
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Toast.makeText(context, "Prueba para ver si entra al recycler", Toast.LENGTH_LONG).show();

                                llenarLista();

                                CarpetasAdapter adapter = new CarpetasAdapter(carpetasNoEliminadas, getActivity());
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,"Error en el 3 recycler -> " + error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(request3);
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
                        crearCarpeta();
                    }
                });

                //botón eliminar carpeta
                builder.setNegativeButton("Eliminar carpetas", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Has clicado en elimimar carpeta", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getContext(), EliminarCarpetas.class);
                        startActivityForResult(intent, CODIGO_PARA_ELIMINAR_CARPETAS); //esto sirve para q al iniciar la actividad, el programa ya este esperando que le devuelva algo
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

                        //aqui vamos al codigo de bet de SubirFotoFragment
                        Fragment myfragment3 = new SubirFotoFragment();
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_drawer, myfragment3).commit();
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

    private void crearCarpeta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Crear carpeta");

        EditText editText = new EditText(requireContext());
        builder.setView(editText);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String carpetaTitulo = editText.getText().toString();
                Toast.makeText(requireContext(), "Creada carpeta: " + carpetaTitulo, Toast.LENGTH_LONG).show();
                CarpetasData nuevaCarpeta = new CarpetasData(carpetaTitulo, idImagen);
                todasLasCarpetas.add(nuevaCarpeta);
                carpetasNoEliminadas.add(nuevaCarpeta);
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODIGO_PARA_ELIMINAR_CARPETAS && resultCode == Activity.RESULT_OK) {
            Toast.makeText(context, "Aqui se ve la lista sin los borrados", Toast.LENGTH_LONG).show();

            showRecycler(3);

        } else {
            Toast.makeText(context, "Algo fue mal volviendo al fragment", Toast.LENGTH_LONG).show();
        }
    }

    //esto crea una lista de tipo CarpetasData con las carpetas no seleccionadas en EliminarCarpetas (importada del adapter)
    private List<CarpetasData> llenarLista() {
        List<ECData> carpetasNoBorradas = ecadapter.getNotSelected();

        for (ECData carpeta : carpetasNoBorradas) {
            String nombre_carpeta = carpeta.getNombre();
            CarpetasData NC = new CarpetasData(nombre_carpeta, idImagen);
            carpetasNoEliminadas.add(NC);
        }

        return carpetasNoEliminadas;
    }

    //esto crea una lista de tipo ECData q tiene la misma info q todasLasCarpetas
    private List<ECData> llenarList() {

        for(CarpetasData carpeta : todasLasCarpetas) {
            String nombre_carpeta = carpeta.getNombre();
            ECData data = new ECData(nombre_carpeta);
            carpetasEliminar.add(data);
        }

        return carpetasEliminar;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editar = view.findViewById(R.id.lapiz);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ModificarInfo.class);
                startActivity(intent);

            }
        });

    }
}