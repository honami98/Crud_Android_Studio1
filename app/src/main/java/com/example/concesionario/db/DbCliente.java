package com.example.concesionario.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbCliente extends DbHelper {
    private String id;
    private String nombre;
    private String correo;
    private char activo;
    Context context;


    public DbCliente(@Nullable Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String id) {
        super(context, name, factory, version);
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setActivo(char activo) {
        this.activo = activo;
    }

    /*public static List<dbCliente> getAllClientes(Context context) {
        List<dbCliente> clientes = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("cliente", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String correo = cursor.getString(cursor.getColumnIndex("correo"));
                String activo = cursor.getString(cursor.getColumnIndex("activo"));
                clientes.add(new dbCliente(id, nombre, correo, activo));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return clientes;
    }*/

}


