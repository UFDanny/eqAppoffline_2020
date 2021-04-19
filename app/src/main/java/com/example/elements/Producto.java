package com.example.elements;

import java.sql.Time;

public class Producto {
    private int id;
    private String name;
    private String descripcion;
    private int cantidad_produccion;
    private Time time_produccion;
    private int status;

    public Producto(int id, String name, String descripcion, int cantidad_produccion, Time time_produccion, int status) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.cantidad_produccion = cantidad_produccion;
        this.time_produccion = time_produccion;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad_produccion() {
        return cantidad_produccion;
    }

    public void setCantidad_produccion(int cantidad_produccion) {
        this.cantidad_produccion = cantidad_produccion;
    }

    public Time getTime_produccion() {
        return time_produccion;
    }

    public void setTime_produccion(Time time_produccion) {
        this.time_produccion = time_produccion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
