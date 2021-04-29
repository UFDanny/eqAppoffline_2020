package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.controller.DbSqlite;
import com.example.controller.crudDB;
import com.example.models.ModelProducto;

import java.sql.Time;
import java.util.Calendar;

public class nuevoProducto extends AppCompatActivity {
    public EditText edittext_nombre;
    public EditText edittext_cantidad;
    public EditText edittext_status;
    public EditText edittext_time;
    public EditText edittext_descripcion;
    public Button button_guardar;
    public crudDB db;
    public AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
        edittext_nombre = (EditText)findViewById(R.id.name_nuevo_producto);
        edittext_cantidad = (EditText)findViewById(R.id.cantidad_nuevo_producto);
        edittext_status = (EditText)findViewById(R.id.status_nuevo_producto);
        edittext_time = (EditText)findViewById(R.id.time_nuevo_producto);
        edittext_descripcion = (EditText)findViewById(R.id.descripcion_nuevo_producto);
        button_guardar = (Button) findViewById(R.id.btncrearnuevoproducto);
        builder = new AlertDialog.Builder(this);
    }

    public void dialogTime(View v){
        int mHour, mMinute, mSecond;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    edittext_time.setText(hourOfDay + ":" + minute+ ":00" );
            }
            }, mHour, mMinute, true);
            timePickerDialog.show();
    }

    public void clickGuardar(View v){
        db = crudDB.obtenerInstancia(getApplicationContext());
        if (!edittext_nombre.getText().toString().isEmpty() && !edittext_cantidad.getText().toString().isEmpty() && !edittext_descripcion.getText().toString().isEmpty() && !edittext_status.getText().toString().isEmpty() && !edittext_time.getText().toString().isEmpty()) {
            builder.setTitle("Resumen");
            String message = "Nombre: "+edittext_nombre.getText()+"\n"+
                    "Descripcion: "+edittext_descripcion.getText()+"\n"+
                    "Cantidad Produccción: "+ edittext_cantidad.getText()+"\n"+
                    "Status: "+ edittext_status.getText()+"\n"+
                    "Tiempo: "+ edittext_time.getText()+"\n";
            builder.setMessage(message);
            builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean result = db.insertarProducto(new ModelProducto(0, edittext_nombre.getText().toString(), edittext_descripcion.getText().toString(), Integer.parseInt(edittext_cantidad.getText().toString()), Time.valueOf(edittext_time.getText().toString()), Integer.parseInt(edittext_status.getText().toString())));
                    if (result) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Producto almacenado", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Error al guardar", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
            builder.setNegativeButton("Cancelar", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else{
            Toast toast = Toast.makeText(this, "Algunos campos estan vacios", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}