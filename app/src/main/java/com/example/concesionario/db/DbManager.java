package com.example.concesionario.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {

    private DbHelper conexion;
    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "dbconcesionario";

    private static final int DATABASE_VERSION = 1;

    public DbManager(Context context) {
        conexion=new DbHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public static final String createClienteTable = "CREATE TABLE cliente (" +
            "id TEXT PRIMARY KEY," +
            "nombre TEXT not null," +
            "correo TEXT not null," +
            "activo TEXT default 'si');";

    public static final String createVehiculoTable = "CREATE TABLE vehiculo (" +
            "placa TEXT PRIMARY KEY," +
            "marca TEXT not null," +
            "modelo TEXT not null," +
            "activo TEXT default 'si');";

    public static final String createVentaTable = "CREATE TABLE venta (" +
            "codigo TEXT PRIMARY KEY," +
            "id_cliente TEXT," +
            "placa_vehiculo TEXT," +
            "fecha DATE not null," +
            "FOREIGN KEY(id_cliente) REFERENCES cliente(id)," +
            "FOREIGN KEY(placa_vehiculo) REFERENCES vehiculo(placa)" +
            ");";
}
