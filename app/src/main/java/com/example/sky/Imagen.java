package com.example.sky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class Imagen extends AppCompatActivity {
    ImageView foto;
    TextView usuario;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        context = this;

        //Obtenemos los intent
        Intent intent = getIntent();
        String imagen_url = intent.getStringExtra("image_url");

        //Iniciamos la foto
        foto = findViewById(R.id.foto_predeterminada);

        //Cargamos la imagen usando Glide para la foto
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);


        //Mostramos la imagen en el ImageView
        Glide.with(context)
                .load(imagen_url)
                .apply(requestOptions)
                .into(foto);

        usuario = findViewById(R.id.usuario);

        usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the fragment transaction
                Fragment otrosPerfilesFragment = new OtrosPerfilesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, otrosPerfilesFragment)
                        .commit();
            }
        });

    }
}