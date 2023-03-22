package com.example.concesionario.db;

import android.content.Context;

import androidx.annotation.Nullable;

public class DbVehiculo {

    Context context;
    private String placa;

    private String modelo;

    private String marca;

    private char activo;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public char getActivo() {
        return activo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }



}
