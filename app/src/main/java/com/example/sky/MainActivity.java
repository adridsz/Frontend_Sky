package com.example.sky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment myfragment3 = new OtrosPerfilesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, myfragment3).commit();

    }
}