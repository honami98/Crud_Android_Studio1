package com.example.concesionario.db;

import android.content.Context;

import androidx.annotation.Nullable;

public class DbVenta {


    private int codigo;
    private String fecha_venta;

    private int id_cliente;

    private String placa_vehiculo;

    public DbVenta(int codigo, String fecha_venta){
        this.codigo=codigo;
        this.fecha_venta=fecha_venta;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }


}
