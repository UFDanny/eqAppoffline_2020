package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void runProducto(View v)
    {
        Intent i = new Intent(this, Productos.class);
        startActivity(i);
    }
    public  void runRegistro(View v)
    {
        Intent i = new Intent(this, RegistroHora.class);
        startActivity(i);
    }
}