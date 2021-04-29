package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controller.crudDB;
import com.example.models.ModelHoraRegistro;
import com.example.models.ModelProducto;

import java.text.ParseException;
import java.util.ArrayList;

public class RegistroHora extends AppCompatActivity {

    ListView simpleList;
    public AlertDialog.Builder builder;
    public AlertDialog.Builder builder_delete;
    public crudDB db;
    public Toolbar toolbar;
    public ArrayList<ModelHoraRegistro> listaRegistros;
    public SearchView searchView;
    public ArrayAdapter<ModelHoraRegistro> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_hora);
        toolbar = findViewById(R.id.toolbarregistro);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroHora.super.onBackPressed();
            }
        });

        builder = new AlertDialog.Builder(this);
        builder_delete = new AlertDialog.Builder(this);
        listaRegistros = new ArrayList<>();
        cargarRegistros();
        searchView = (SearchView) findViewById(R.id.searchViewregistro);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                for(int i=0;i<listaRegistros.size();i++){
                    if (listaRegistros.get(i).getDate().equals(s)){
                        arrayAdapter.getFilter().filter(s);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarRegistros();
    }

    public void cargarRegistros(){
        db = crudDB.obtenerInstancia(getApplicationContext());
        listaRegistros = db.listaRegistros();
        if (listaRegistros.isEmpty()){
            Toast toast = Toast.makeText(this, "No existen Registros", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            simpleList = (ListView)findViewById(R.id.ListViewRegistroHora);
            arrayAdapter = new ArrayAdapter<ModelHoraRegistro>(this, R.layout.activity_listview, R.id.itemtextView, listaRegistros);
            simpleList.setAdapter(arrayAdapter);
            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                    // setup the alert builder
                    builder.setTitle(listaRegistros.get(position).getDate().toString());
                    String message = "Hora de Inicio: "+listaRegistros.get(position).getHora_inicio()+"\n"+
                            "Hora de Fin: "+ listaRegistros.get(position).getHora_fin()+"\n"+
                            "Id Producto: "+ listaRegistros.get(position).getProduct_id()+"\n"+
                            "Valor Planeado: "+ listaRegistros.get(position).getValor_planeado()+"\n"+
                            "Valor Real: "+ listaRegistros.get(position).getValor_real();
                    builder.setMessage(message);
                    builder.setPositiveButton("Editar", null);
                    builder.setNeutralButton("Eliminar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            builder_delete.setTitle("Esta seguro de eliminar el registro?");
                            builder_delete.setMessage("Esta acción es irreversible");
                            builder_delete.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (db.eliminarRegistroHora(listaRegistros.get(position))){
                                        Toast.makeText(getApplicationContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
                                        listaRegistros.remove(position);
                                        arrayAdapter.notifyDataSetChanged();
                                    }
                                    else{
                                        Toast toast = Toast.makeText(getApplicationContext(), "No se puede eliminar", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                }
                            });
                            builder_delete.setNegativeButton("No",null);
                            AlertDialog del = builder_delete.create();
                            del.show();

                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    // create and show the alert dialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_registro:
                Intent i = new Intent(this, nuevoRegistro.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}