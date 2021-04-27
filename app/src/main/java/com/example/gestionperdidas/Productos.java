package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Productos extends AppCompatActivity {

    ListView simpleList;
    String listProductos[] = {"producto1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        simpleList = (ListView)findViewById(R.id.ListViewProducto);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, listProductos);
        simpleList.setAdapter(arrayAdapter);
    }

    public void runNuevoProducto(View v)
    {
        Intent i = new Intent(this, nuevoProducto.class);
        startActivity(i);
    }
}