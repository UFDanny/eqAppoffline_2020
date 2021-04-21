package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RegistroHora extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_hora);
    }

    public void runNuevoRegistro(View v)
    {
        Intent i = new Intent(this, nuevoRegistro.class);
        startActivity(i);
    }
}