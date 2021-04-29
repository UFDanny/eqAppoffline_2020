package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.controller.crudDB;
import com.example.models.ModelHoraRegistro;
import com.example.models.ModelProducto;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

public class nuevoRegistro extends AppCompatActivity {

    public EditText editText_date;
    public EditText editText_time_inicio;
    public EditText editText_time_fin;
    public EditText editText_producto;
    public EditText editText_valor_planeado;
    public EditText editText_valor_real;
    public Button button_guardar;
    public crudDB db;
    public ArrayList<ModelProducto> listaProductos;
    public ModelProducto producto_seleccionado;
    public AlertDialog.Builder builder_nuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_registro);
        db = crudDB.obtenerInstancia(getApplicationContext());
        builder_nuevo = new AlertDialog.Builder(this);
        listaProductos = new ArrayList<>();
        listaProductos = db.listaProductos();
        editText_date = (EditText) findViewById(R.id.fecha_nuevo_registro);
        editText_time_inicio = (EditText) findViewById(R.id.hora_inicio_registro);
        editText_time_fin = (EditText) findViewById(R.id.hora_fin_registro);
        editText_producto = (EditText) findViewById(R.id.id_producto_registro);
        editText_valor_planeado = (EditText) findViewById(R.id.valor_planeado_registro);
        editText_valor_real = (EditText) findViewById(R.id.valor_real_registro);
        button_guardar = (Button) findViewById(R.id.btncrearnuevoregistro);

    }

    public void clickDate(View v){
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                   editText_date.setText( year +"-"+ (monthOfYear + 1) + "-" +dayOfMonth);
                   }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void clickTimer(View v){
        int mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        if ( v == editText_time_inicio){
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                    editText_time_inicio.setText(hourOfDay + ":" + minute+ ":00");
                }
                }, mHour, mMinute, true);
            timePickerDialog.show();
        }
        if (v == editText_time_fin){
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                    editText_time_fin.setText(hourOfDay + ":" + minute+ ":00");
                }
            }, mHour, mMinute, true);
            timePickerDialog.show();
        }
    }

    public void clickProducto(View v){
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setTitle("Elija un producto");
        ArrayAdapter arrayAdapter = new ArrayAdapter<ModelProducto>(this, R.layout.activity_listview, R.id.itemtextView, listaProductos);
        alertbox.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                producto_seleccionado = listaProductos.get(item);
                editText_producto.setText(producto_seleccionado.toString());
            }
        });
        AlertDialog alert = alertbox.create();
        alert.show();
    }

    public void clickGuardar(View v){
        if (!editText_date.getText().toString().isEmpty() && !editText_time_inicio.getText().toString().isEmpty() && !editText_time_fin.getText().toString().isEmpty() && !editText_producto.getText().toString().isEmpty() && !editText_valor_planeado.getText().toString().isEmpty() && !editText_valor_real.getText().toString().isEmpty()) {
            builder_nuevo.setTitle("Resumen");
            String message = "Fecha: "+editText_date.getText()+"\n"+
                    "Hora de inicio: "+editText_time_inicio.getText()+"\n"+
                    "Hora de fin: "+ editText_time_fin.getText()+"\n"+
                    "Producto: "+ editText_producto.getText()+"\n"+
                    "Valor planeado: "+ editText_valor_planeado.getText()+"\n"+
                    "Valor Real: "+ editText_valor_real.getText()+"\n";
            builder_nuevo.setMessage(message);
            builder_nuevo.setPositiveButton("Guardar", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean result = db.insertarRegistroHora(new ModelHoraRegistro(0, Date.valueOf(editText_date.getText().toString()),Time.valueOf(editText_time_inicio.getText().toString()),Time.valueOf(editText_time_fin.getText().toString()),producto_seleccionado.getId(),Integer.parseInt(editText_valor_planeado.getText().toString()),Integer.parseInt(editText_valor_real.getText().toString())));
                    if (result) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Registro almacenado", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Error al guardar", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
            builder_nuevo.setNegativeButton("Cancelar", null);
            AlertDialog dialog = builder_nuevo.create();
            dialog.show();
        }
        else{
            Toast toast = Toast.makeText(this, "Algunos campos estan vacios", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}