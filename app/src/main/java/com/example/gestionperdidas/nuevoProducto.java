package com.example.gestionperdidas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class nuevoProducto extends AppCompatActivity {
    public EditText edittext_nombre;
    public EditText edittext_cantidad;
    public EditText edittext_status;
    public EditText edittext_time;
    public EditText edittext_descripcion;
    public Button button_guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
        edittext_nombre = (EditText)findViewById(R.id.name_nuevo_producto);
        edittext_cantidad = (EditText)findViewById(R.id.cantidad_nuevo_producto);
        edittext_status = (EditText)findViewById(R.id.status_nuevo_producto);
        edittext_time = (EditText)findViewById(R.id.time_nuevo_producto);
        edittext_descripcion = (EditText)findViewById(R.id.descripcion_nuevo_producto);
        button_guardar = (Button) findViewById(R.id.btnnuevoregistro);
    }

    public void dialogTime(View v){
        int mHour, mMinute;
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    edittext_time.setText(hourOfDay + ":" + minute);
            }
            }, mHour, mMinute, true);
            timePickerDialog.show();
    }

    public void clickGuardar(View v){
        Toast toast = Toast.makeText(this, "Producto   almacenado", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
}