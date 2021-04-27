package com.example.controller;

import android.content.Context;

import com.example.models.ModelHoraRegistro;
import com.example.models.ModelProducto;

import java.util.ArrayList;

public class crudDB {
    private static DbSqlite baseDatos;
    private static crudDB instancia = new crudDB();

    private crudDB() {
    }

    public static crudDB obtenerInstancia(Context contexto) {
        if (baseDatos == null) {
            baseDatos = new DbSqlite(contexto);
        }
        return instancia;
    }

    //[CRUD producto]
    public boolean insertarProducto(ModelProducto producto){
        return true;
    }

    public boolean modificarProducto(ModelProducto producto){
        return true;
    }

    public boolean eliminarProducto(ModelProducto producto){
        return true;
    }

    public ArrayList<ModelProducto> listaProductos(){
        ArrayList<ModelProducto> productos = new ArrayList<ModelProducto>();
        return productos;
    }

    public ModelProducto consultaProducto(int id){
        return null;
    }

    //[CRUD registro]
    public boolean insertarRegistroHora(ModelHoraRegistro registro){
        return true;
    }

    public boolean modificarRegistroHora(ModelHoraRegistro registro){
        return true;
    }

    public boolean eliminarRegistroHora(ModelHoraRegistro registro){
        return true;
    }

    public ArrayList<ModelHoraRegistro> listaRegistros(){
        ArrayList<ModelHoraRegistro> registros = new ArrayList<ModelHoraRegistro>();
        return registros;
    }

    public ModelHoraRegistro consultaRegistro(int id){
        return null;
    }





}
