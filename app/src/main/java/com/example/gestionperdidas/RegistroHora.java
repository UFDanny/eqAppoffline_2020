package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RegistroHora extends AppCompatActivity {

    ListView simpleList;
    String listRegistro[] = {"registro1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_hora);

        simpleList = (ListView)findViewById(R.id.ListViewRegistroHora);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, listRegistro);
        simpleList.setAdapter(arrayAdapter);
    }

    public void runNuevoRegistro(View v)
    {
        Intent i = new Intent(this, nuevoRegistro.class);
        startActivity(i);
    }
}