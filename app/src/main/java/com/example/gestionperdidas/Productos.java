package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controller.crudDB;
import com.example.models.ModelProducto;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {

    ListView simpleList;
    public AlertDialog.Builder builder;
    public AlertDialog.Builder builder_delete;
    public crudDB db;
    public Toolbar toolbar;
    public ArrayList<ModelProducto> listaProductos;
    public SearchView searchView;
    public ArrayAdapter<ModelProducto> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        toolbar = findViewById(R.id.toolbarproducto);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Productos.super.onBackPressed();
            }
        });

        builder = new AlertDialog.Builder(this);
        builder_delete = new AlertDialog.Builder(this);
        listaProductos = new ArrayList<>();
        cargarProductos();
        searchView = (SearchView) findViewById(R.id.searchViewproducto);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                for(int i=0;i<listaProductos.size();i++){
                    if (listaProductos.get(i).getName().equals(s)){
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
        cargarProductos();
    }

    public void cargarProductos(){
        db = crudDB.obtenerInstancia(getApplicationContext());
        listaProductos = db.listaProductos();
        if (listaProductos.isEmpty()){
            Toast toast = Toast.makeText(this, "No existen Productos", Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            simpleList = (ListView)findViewById(R.id.ListViewProducto);
            arrayAdapter = new ArrayAdapter<ModelProducto>(this, R.layout.activity_listview, R.id.itemtextView, listaProductos);
            simpleList.setAdapter(arrayAdapter);
            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int position, long id) {
                    // setup the alert builder
                    builder.setTitle(listaProductos.get(position).getName());
                    String message = "Descripcion: \n"+listaProductos.get(position).getDescripcion()+"\n"+
                            "Cantidad Produccción: "+ listaProductos.get(position).getCantidad_produccion()+"\n"+
                            "Status: "+ listaProductos.get(position).getStatus()+"\n"+
                            "Tiempo: "+ listaProductos.get(position).getTime_produccion()+"\n";
                    builder.setMessage(message);
                    builder.setPositiveButton("Editar", null);
                    builder.setNeutralButton("Eliminar", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            builder_delete.setTitle("Esta seguro de eliminar el producto?");
                            builder_delete.setMessage("Esta acción es irreversible");
                            builder_delete.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (db.eliminarProducto(listaProductos.get(position))){
                                        Toast.makeText(getApplicationContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
                                        listaProductos.remove(position);
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
        getMenuInflater().inflate(R.menu.menu_producto, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_producto:
                Intent i = new Intent(this, nuevoProducto.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}