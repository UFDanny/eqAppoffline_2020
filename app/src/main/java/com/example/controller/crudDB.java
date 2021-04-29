package com.example.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.ModelHoraRegistro;
import com.example.models.ModelProducto;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DbSqlite.TBPROD_COL_NOMBRE,producto.getName());
        values.put(DbSqlite.TBPROD_COL_DESCRIPCION,producto.getDescripcion());
        values.put(DbSqlite.TBPROD_COL_CANTIDAD,producto.getCantidad_produccion());
        values.put(DbSqlite.TBPROD_COL_TIME, (String) producto.getTime_produccion().toString());
        values.put(DbSqlite.TBPROD_COL_STATUS,producto.getStatus());

        long newRowId = db.insert(DbSqlite.TABLE_PRODUCTO, null, values);
        if (newRowId == -1)
                return false;
        return true;
    }

    public boolean modificarProducto(ModelProducto producto){
        return true;
    }

    public boolean eliminarProducto(ModelProducto producto){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = DbSqlite.TBPROD_COL_ID + "=?";
        String[] whereArgs = {String.valueOf(producto.getId())};
        int resultado = db.delete(DbSqlite.TABLE_PRODUCTO, whereClause, whereArgs);
        return resultado > 0;
    }

    public ArrayList<ModelProducto> listaProductos(){
        ArrayList<ModelProducto> productos = new ArrayList<ModelProducto>();
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        Cursor prod = db.rawQuery("select * from " + DbSqlite.TABLE_PRODUCTO, null);
        prod.moveToFirst();
        while (prod.isAfterLast() == false) {
            if ((prod != null) && (prod.getCount() > 0)) {
                int id= prod.getInt(prod.getColumnIndex(DbSqlite.TBPROD_COL_ID));
                String name = prod.getString(prod.getColumnIndex(DbSqlite.TBPROD_COL_NOMBRE));
                String descripcion = prod.getString(prod.getColumnIndex(DbSqlite.TBPROD_COL_DESCRIPCION));
                int cantidad = prod.getInt(prod.getColumnIndex(DbSqlite.TBPROD_COL_CANTIDAD));
                Time time = Time.valueOf(prod.getString(prod.getColumnIndex(DbSqlite.TBPROD_COL_TIME)));
                int status = prod.getInt(prod.getColumnIndex(DbSqlite.TBPROD_COL_STATUS));
                ModelProducto p = new ModelProducto(id,name,descripcion,cantidad,time,status);
                productos.add(p);
            }
            prod.moveToNext();
        }
        return productos;
    }

    public ModelProducto consultaProducto(int id){
        return null;
    }

    //[CRUD registro]
    public boolean insertarRegistroHora(ModelHoraRegistro registro){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbSqlite.TBREG_COL_DATE,registro.getDate().toString());
        values.put(DbSqlite.TBREG_COL_HORAINICIO,registro.getHora_inicio().toString());
        values.put(DbSqlite.TBREG_COL_HORAFIN,registro.getHora_fin().toString());
        values.put(DbSqlite.TBREG_COL_PRODUCTO,registro.getProduct_id());
        values.put(DbSqlite.TBREG_COL_VALPLANEADO,registro.getValor_planeado());
        values.put(DbSqlite.TBREG_COL_VALREAL,registro.getValor_real());

        long newRowId = db.insert(DbSqlite.TABLE_REGISTRO_HORA, null, values);
        if (newRowId == -1)
            return false;
        return true;
    }

    public boolean modificarRegistroHora(ModelHoraRegistro registro){
        return true;
    }

    public boolean eliminarRegistroHora(ModelHoraRegistro registro){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String whereClause = DbSqlite.TBREG_COL_ID + "=?";
        String[] whereArgs = {String.valueOf(registro.getId())};
        int resultado = db.delete(DbSqlite.TABLE_REGISTRO_HORA, whereClause, whereArgs);
        return resultado > 0;
    }

    public ArrayList<ModelHoraRegistro> listaRegistros(){
        ArrayList<ModelHoraRegistro> registros = new ArrayList<ModelHoraRegistro>();
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        Cursor reg = db.rawQuery("select * from " + DbSqlite.TABLE_REGISTRO_HORA, null);
        reg.moveToFirst();
        while (reg.isAfterLast() == false) {
            if ((reg != null) && (reg.getCount() > 0)) {
                int id= reg.getInt(reg.getColumnIndex(DbSqlite.TBREG_COL_ID));
                String d = reg.getString(reg.getColumnIndex(DbSqlite.TBREG_COL_DATE));
                Date date = Date.valueOf(d);
                Time inicio = Time.valueOf(reg.getString(reg.getColumnIndex(DbSqlite.TBREG_COL_HORAINICIO)));
                Time fin = Time.valueOf(reg.getString(reg.getColumnIndex(DbSqlite.TBREG_COL_HORAFIN)));
                int prod = reg.getInt(reg.getColumnIndex(DbSqlite.TBREG_COL_PRODUCTO));
                int valp = reg.getInt(reg.getColumnIndex(DbSqlite.TBREG_COL_VALPLANEADO));
                int valr = reg.getInt(reg.getColumnIndex(DbSqlite.TBREG_COL_VALREAL));

                ModelHoraRegistro r = new ModelHoraRegistro(id,date,inicio,fin,prod,valp,valr);
                registros.add(r);
            }
            reg.moveToNext();
        }
        return registros;
    }

    public ModelHoraRegistro consultaRegistro(int id){
        return null;
    }





}
