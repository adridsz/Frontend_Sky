package com.example.sky;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sky.databinding.ActivityDrawerBinding;

public class Drawer extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityDrawerBinding binding;


    //Aquí inflamos el diseño de la actividad usando el ActivityDrawerbinding generado
    //y se establece como contenido de la actividad.
    //Además, se configura la barra de herramientas ('Toolbar') como la barra de la aplicación
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarDrawer.toolbar);


        //Obtenemos las referencias al drawer y a la vista de navegación del diseño inflado
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //Creamos una configuración de la barra de la aplicación (AppBarConfiguration) con los
        // destinos principales de la navegación y se asigna el cajón como el diseño abierto.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

        //Configuramos la navegación y la action bar para utilizar el controlador de
        // navegación y la configuración de la barra de la aplicación.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menú, así añadimos ítems a la action bar
        //En este caso, tenemos añadidos dos ítems: Modificar Fuentes y Modificar Temas (en los 3 puntitos)
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }


    //Este método se llama cuando se presiona el botón de navegación hacia arriba (botón de la flecha haica atrás) en la barra de acción
    //Su propósito es manejar la navegación "hacia arriba" en la jerarquía de actividades. En este caso,
    //utilizamos el controlador de navegación para realizar la navegación hacia arriba
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    //MODIFICAR TEMAS Y MODIFICAR FUENTES
    //En este método manejamos los eventos de clic en los elementos del menú de opciones de la action bar.
    // Dependiendo del elemento seleccionado, iniciamos la actividad correspondiente.
    //(LOS TRES PUNTITOS)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Aquí iniciamos la actividad de modificar temas
        if (id == R.id.mod_temas) {
            Intent intent = new Intent(this, ModificarTemas.class);
            startActivity(intent);
            return true;
        }
        //Aquí iniciamos la actividad de modificar fuentes
        if (id == R.id.mod_fuentes) {
            Intent intent = new Intent(this, ModificarFuentes.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}