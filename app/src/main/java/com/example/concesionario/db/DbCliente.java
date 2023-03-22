package com.example.concesionario.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbCliente extends DbHelper{
    private String id;
    private String nombre;
    private String correo;
    private char activo;
    Context context;

    /*public dbCliente(int id, String nombre, String correo, char activo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.activo = activo;
    }*/

    public DbCliente(@Nullable Context context){
        super(context);
        this.context=context;
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
        dbHelper.close();
        return clientes;
    }*/

    public long insertCliente(String id, String nombre, String correo) {
        long colafectadaid=0;
        try{
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", id);
            values.put("nombre", nombre);
            values.put("correo", correo);
            values.put("activo", "si");
            colafectadaid = db.insert(TABLA_CLIENTE, null, values);
            db.close();
            dbHelper.close();

        }catch (Exception e){
            e.toString();
        }
            return colafectadaid;
    }


}


