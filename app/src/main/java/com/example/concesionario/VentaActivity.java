package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.concesionario.db.DbHelper;

public class VentaActivity extends AppCompatActivity {

    EditText txtCodigo,txtFecha,txtId,txtPlaca;
    String codigo,fecha,id,placa;
    CheckBox activo;
    long respuesta;
    byte sw;

    DbHelper dbHelper= new DbHelper(this,"dbconcesionario.db",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        txtCodigo=findViewById(R.id.txtCodigoVenta);
        txtFecha=findViewById(R.id.txtFecha);
        txtId=findViewById(R.id.txtIdCliente);
        txtPlaca=findViewById(R.id.txtPlacaVehiculo);
        sw=0;
    }

    public void Home(View view){
        Intent inthome=new Intent(this,MainActivity.class);
        startActivity(inthome);
    }

    public void guardarVenta(View View){
        codigo=txtCodigo.getText().toString();
        fecha=txtFecha.getText().toString();
        id=txtId.getText().toString();
        placa=txtPlaca.getText().toString();

        if(codigo.isEmpty() || fecha.isEmpty() || id.isEmpty() || placa.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            txtCodigo.requestFocus();
        }else{
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("codigo",codigo);
            registro.put("id_cliente",id);
            registro.put("placa_vehiculo",placa);
            registro.put("fecha",fecha);

            if (sw==0)
                respuesta=db.insert("venta",null,registro);
            else{
                respuesta=db.update("venta",registro,"codigo='"+codigo+"'",null);
                sw=0;
            }

            if (respuesta == 0){
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Venta Registrada", Toast.LENGTH_SHORT).show();
                limpiar();
            }
            db.close();
        }
    }


    public void consultarIdVenta(View view){
        id=txtId.getText().toString();
        if(!id.isEmpty()){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from cliente where id='"+id+"'",null);
            if(fila.moveToNext()){
                if (fila.getString(3).equals("si")){
                    Toast.makeText(this, "Cliente Registrado y activo", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Cliente Registrado inactivo, cambie su estado antes de realizar el registro", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }else{
            Toast.makeText(this, "Introduzca primero una identificación para buscar", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarPlacaVenta(View view){
        placa=txtPlaca.getText().toString();
        if(!placa.isEmpty()){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from vehiculo where placa='"+placa+"'",null);
            if(fila.moveToNext()){
                if (fila.getString(3).equals("si")){
                    Toast.makeText(this, "Vehiculo Registrado y activo", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Vehiculo Registrado inactivo, cambie su estado antes de realizar el registro", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Registro no encontrado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }else{
            Toast.makeText(this, "Introduzca primero una placa para buscar", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultarCodigo(View view){
        codigo=txtCodigo.getText().toString();

        if(!codigo.isEmpty()){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from venta where codigo='"+codigo+"'",null);
            if (fila.moveToNext()){
                sw=1;
                txtId.setText(fila.getString(1));
                txtPlaca.setText(fila.getString(2));
                txtFecha.setText(fila.getString(3));
            }else{
                Toast.makeText(this, "Código de venta no encontrado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }else{
            Toast.makeText(this, "Código de venta requerido para consultar", Toast.LENGTH_SHORT).show();
            txtCodigo.requestFocus();
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtFecha.setText("");
        txtId.setText("");
        txtPlaca.setText("");
    }
}