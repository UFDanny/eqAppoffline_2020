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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class nuevoRegistro extends AppCompatActivity {

    public EditText editText_date;
    public EditText editText_time_inicio;
    public EditText editText_time_fin;
    public EditText editText_producto;
    public EditText editText_valor_planeado;
    public EditText editText_valor_real;
    public Button button_guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_registro);

        editText_date = (EditText) findViewById(R.id.fecha_nuevo_registro);
        editText_time_inicio = (EditText) findViewById(R.id.hora_inicio_registro);
        editText_time_fin = (EditText) findViewById(R.id.hora_fin_registro);
        editText_producto = (EditText) findViewById(R.id.id_producto_registro);
        editText_valor_planeado = (EditText) findViewById(R.id.valor_planeado_registro);
        editText_valor_real = (EditText) findViewById(R.id.valor_real_registro);
        button_guardar = (Button) findViewById(R.id.btnnuevoregistro);

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
                   editText_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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
                    editText_time_inicio.setText(hourOfDay + ":" + minute);
                }
                }, mHour, mMinute, true);
            timePickerDialog.show();
        }
        if (v == editText_time_fin){
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                    editText_time_fin.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, true);
            timePickerDialog.show();
        }
    }

    public void clickProducto(View v){
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        final String listProductos[] = {"producto1","producto2","producto3","producto2","producto3","producto2","producto3","producto2","producto3","producto2","producto3","producto2","producto3","producto2","producto3","producto2","producto3","producto2","producto3"};
        alertbox.setTitle("Elija un producto").setItems(listProductos, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int pos) {
                        editText_producto.setText(listProductos[pos]);
                    }
                });
        alertbox.show();
    }

    public void clickGuardar(View v){
        Toast toast = Toast.makeText(this, "Registro almacenado", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}