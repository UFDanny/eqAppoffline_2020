package com.example.models;

import java.util.Date;
import java.sql.Time;

public class ModelHoraRegistro {
    private int id;
    private Date date;
    private Time hora_inicio;
    private Time hora_fin;
    private int product_id;
    private int valor_planeado;
    private int valor_real;

    public ModelHoraRegistro(int id, Date date, Time hora_inicio, Time hora_fin, int product_id, int valor_planeado, int valor_real) {
        this.id = id;
        this.date = date;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.product_id = product_id;
        this.valor_planeado = valor_planeado;
        this.valor_real = valor_real;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getValor_planeado() {
        return valor_planeado;
    }

    public void setValor_planeado(int valor_planeado) {
        this.valor_planeado = valor_planeado;
    }

    public int getValor_real() {
        return valor_real;
    }

    public void setValor_real(int valor_real) {
        this.valor_real = valor_real;
    }
}
