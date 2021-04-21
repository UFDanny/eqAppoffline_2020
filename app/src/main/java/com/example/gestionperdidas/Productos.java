package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Productos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
    }

    public void runNuevoProducto(View v)
    {
        Intent i = new Intent(this, nuevoProducto.class);
        startActivity(i);
    }
}