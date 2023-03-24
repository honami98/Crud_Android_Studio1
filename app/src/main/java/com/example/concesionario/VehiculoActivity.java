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

public class VehiculoActivity extends AppCompatActivity {

    EditText txtPlaca, txtMarca,txtModelo;
    String placa,marca,modelo;
    CheckBox activo;

    long respuesta;
    byte sw;


    DbHelper dbHelper= new DbHelper(this,"dbconcesionario.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculo);

        txtPlaca=findViewById(R.id.txtPlaca);
        txtMarca=findViewById(R.id.txtMarca);
        txtModelo=findViewById(R.id.txtModelo);
        activo=findViewById(R.id.checkBoxActivo);
        sw=0;
    }

    public void Home(View view){
        Intent inthome=new Intent(this,MainActivity.class);
        startActivity(inthome);
    }

    public void guardarVehiculo(View view){
        placa=txtPlaca.getText().toString();
        marca=txtMarca.getText().toString();
        modelo=txtModelo.getText().toString();

        if(placa.isEmpty() || marca.isEmpty() || modelo.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            txtPlaca.requestFocus();
        }else{
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("placa",placa);
            registro.put("modelo",modelo);
            registro.put("marca",marca);

            if (sw==0)
                respuesta=db.insert("vehiculo",null,registro);
            else{
                respuesta=db.update("vehiculo",registro,"placa='"+placa+"'",null);
                sw=0;
            }

            if (respuesta == 0){
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                limpiar();
            }
            db.close();

        }
    }

    public void consultarVehiculo(View View){
        placa=txtPlaca.getText().toString();
        if(!placa.isEmpty()){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from vehiculo where placa='"+placa+"'",null);
            if (fila.moveToNext()){
                sw=1;
                txtModelo.setText(fila.getString(1));
                txtMarca.setText(fila.getString(2));
                if (fila.getString(3).equals("si")){
                    activo.setChecked(true);
                }else{
                    activo.setChecked(false);
                }
            }else{
                Toast.makeText(this, "Registro no hallado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }else{
            Toast.makeText(this, "La placa es requerida para consultar", Toast.LENGTH_SHORT).show();
            txtPlaca.requestFocus();
        }
    }

    private void limpiar() {
        txtPlaca.setText("");
        txtModelo.setText("");
        txtMarca.setText("");
    }
}