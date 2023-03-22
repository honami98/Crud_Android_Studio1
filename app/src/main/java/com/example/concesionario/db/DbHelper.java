package com.example.concesionario.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbconcesionario.db";
    public static final String TABLA_CLIENTE = "cliente";
    public static final String TABLA_VEHICULO = "vehiculo";
    public static final String TABLA_VENTA = "venta";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createClienteTable = "CREATE TABLE cliente (" +
                "id VARCHAR PRIMARY KEY," +
                "nombre TEXT not null," +
                "correo TEXT not null," +
                "activo TEXT default 'si');";
        db.execSQL(createClienteTable);

        String createVehiculoTable = "CREATE TABLE vehiculo (" +
                "placa VARCHAR PRIMARY KEY," +
                "marca TEXT not null," +
                "modelo TEXT not null," +
                "activo TEXT default 'si');";
        db.execSQL(createVehiculoTable);

        String createVentaTable = "CREATE TABLE venta (" +
                "codigo VARCHAR PRIMARY KEY AUTOINCREMENT," +
                "id_cliente VARCHAR," +
                "id_vehiculo TEXT," +
                "fecha DATE not null," +
                "FOREIGN KEY(id_cliente) REFERENCES cliente(id)," +
                "FOREIGN KEY(id_vehiculo) REFERENCES vehiculo(placa)" +
                ");";
        db.execSQL(createVentaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // no necesario para esta implementación
    }
}

