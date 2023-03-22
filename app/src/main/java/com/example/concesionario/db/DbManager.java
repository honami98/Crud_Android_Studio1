package com.example.concesionario.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {

    private DbHelper conexion;
    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "dbconcesionario";
    public static final String TABLA_CLIENTE = "cliente";
    public static final String TABLA_VEHICULO = "vehiculo";
    public static final String TABLA_VENTA = "venta";
    private static final int DATABASE_VERSION = 1;

    public DbManager(Context context) {
        conexion=new DbHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DbManager open() throws SQLException{
        db=conexion.getWritableDatabase();
        return this;
    }

    public void close(){
        conexion.close();
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
            "id_cliente VARCHAR," +
            "id_vehiculo VARCHAR," +
            "fecha DATE not null," +
            "FOREIGN KEY(id_cliente) REFERENCES cliente(id)," +
            "FOREIGN KEY(id_vehiculo) REFERENCES vehiculo(placa)" +
            ");";
}
