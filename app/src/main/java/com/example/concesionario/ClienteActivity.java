package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.concesionario.db.DbCliente;
import com.example.concesionario.db.DbHelper;

public class ClienteActivity extends AppCompatActivity {

    EditText txtId,txtNombre,txtCorreo;
    String id,nombre,correo;
    CheckBox activo;
    Button btnguardar;
    long respuesta;
    byte sw;


    DbHelper dbHelper= new DbHelper(this,"dbconcesionario.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        txtId=findViewById(R.id.txtId);
        txtNombre=findViewById(R.id.txtNombre);
        txtCorreo=findViewById(R.id.txtCorreo);
        activo=findViewById(R.id.checkBoxActivo);
        sw=0;

    }

    public void Guardar(View view){
        id=txtId.getText().toString();
        nombre=txtNombre.getText().toString();
        correo=txtCorreo.getText().toString();
        if (id.isEmpty() || nombre.isEmpty() || correo.isEmpty()){
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            txtId.requestFocus();
        }else{
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            ContentValues registro=new ContentValues();
            registro.put("id",id);
            registro.put("nombre",nombre);
            registro.put("correo",correo);
            if (sw==0)
                respuesta=db.insert("cliente",null,registro);
            else{
                respuesta=db.update("cliente",registro,"id='"+id+"'",null);
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
    }//fin Metodo de guardar

    public void Consultar(View view){
        //Validando que haya una identificacion
        id=txtId.getText().toString();
        if (!id.isEmpty()){
            SQLiteDatabase db=dbHelper.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from cliente where id='"+id+"'",null);
            if (fila.moveToNext()){
                sw=1;
                txtNombre.setText(fila.getString(1));
                txtCorreo.setText(fila.getString(2));
                if (fila.getString(3).equals("si"))
                    activo.setChecked(true);
                else
                    activo.setChecked(false);
            }else{
                Toast.makeText(this, "Registro no hallado", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }else{
            Toast.makeText(this, "Identificacion es requerida para consultar", Toast.LENGTH_SHORT).show();
            txtId.requestFocus();
        }
    }//Fin del consultar

    public void Home(View view){
        Intent inthome=new Intent(this,MainActivity.class);
        startActivity(inthome);
    }

    private void limpiar(){
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
    }


}
