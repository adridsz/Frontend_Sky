package com.example.sky.ui.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sky.R;

public class Detail_Activity extends AppCompatActivity {

    private ImageView IMG_PEZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //Intent intent = getIntent();

        String imagen = getIntent().getStringExtra("imagen");

        viewDatos(imagen);
    }

    private void viewDatos(String imagen) {

        IMG_PEZ = findViewById(R.id.imagen);
        Glide.with(this).load(imagen).into(IMG_PEZ); //esto hace q se visualice la imagen
    }
}