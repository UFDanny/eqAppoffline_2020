package com.example.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbSqlite extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gestion.db";
    private final Context contexto;

    //tabla producto
    public static final String TABLE_PRODUCTO = "producto";
    public static final String TBPROD_COL_ID = "id_producto";
    public static final String TBPROD_COL_NOMBRE = "nombre";
    public static final String TBPROD_COL_CANTIDAD = "cantidad_produccion";
    public static final String TBPROD_COL_STATUS = "status";
    public static final String TBPROD_COL_TIME = "time_produccion";
    public static final String TBPROD_COL_DESCRIPCION = "descripcion";

    //tabla registro hora-hora
    public static final String TABLE_REGISTRO_HORA = "registro_hora";
    public static final String TBREG_COL_ID = "id";
    public static final String TBREG_COL_DATE = "date";
    public static final String TBREG_COL_HORAINICIO = "hora_inicio";
    public static final String TBREG_COL_HORAFIN = "hora_fin";
    public static final String TBREG_COL_PRODUCTO = "id_producto";
    public static final String TBREG_COL_VALPLANEADO = "valor_planeado";
    public static final String TBREG_COL_VALREAL = "valor_real";

    //sintaxis para crear la tabla producto
    static final String CreateTableProduct = "Create Table IF NOT EXISTS " +TABLE_PRODUCTO + " ("
            + TBPROD_COL_ID +" Integer PRIMARY KEY AUTOINCREMENT,"
            + TBPROD_COL_NOMBRE + " text,"
            + TBPROD_COL_DESCRIPCION + " text,"
            + TBPROD_COL_CANTIDAD + " integer,"
            + TBPROD_COL_TIME + " text,"
            + TBPROD_COL_STATUS + " integer)";

    //sintaxis para crear la tabla registro
    static final String CreateTableRegistro = "Create Table IF NOT EXISTS " +TABLE_REGISTRO_HORA + " ("
            + TBREG_COL_ID +" Integer PRIMARY KEY AUTOINCREMENT,"
            + TBREG_COL_DATE +" Date,"
            + TBREG_COL_HORAINICIO +" text,"
            + TBREG_COL_HORAFIN +" text,"
            + TBREG_COL_PRODUCTO +" integer,"
            + TBREG_COL_VALPLANEADO +" integer,"
            + TBREG_COL_VALREAL + " integer)";

    public DbSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTableProduct);
        db.execSQL(CreateTableRegistro);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table IF EXISTS "+ TABLE_PRODUCTO);
        db.execSQL("Drop table IF EXISTS "+ TABLE_REGISTRO_HORA);
        onCreate(db);
    }
}
