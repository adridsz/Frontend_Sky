package com.example.sky;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.sky.login.LoginActivity;
import com.example.sky.login.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Provisional
        Intent myIntent = new Intent(context, LoginActivity.class);
        context.startActivity(myIntent);

    }
}