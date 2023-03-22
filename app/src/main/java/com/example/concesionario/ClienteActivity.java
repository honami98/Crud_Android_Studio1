package com.example.concesionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.concesionario.db.DbCliente;

public class ClienteActivity extends AppCompatActivity {

    EditText txtId,txtNombre,txtCorreo;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        txtId=findViewById(R.id.txtId);
        txtNombre=findViewById(R.id.txtNombre);
        txtCorreo=findViewById(R.id.txtCorreo);

        btnguardar.findViewById(R.id.btnRegistrarCliente);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();

            }
        });
    }
    private void insertarDatos() {
        DbCliente dbCliente=new DbCliente(ClienteActivity.this);
        long colafectadaid;

        colafectadaid= dbCliente.insertCliente(txtId.getText().toString(),txtNombre.getText().toString(),txtCorreo.getText().toString());

        if (colafectadaid >0) {
            Toast.makeText(ClienteActivity.this, "Cliente registrado correctamente", Toast.LENGTH_SHORT).show();
            limpiar();
        } else {
            Toast.makeText(ClienteActivity.this, "Error al registrar el cliente", Toast.LENGTH_SHORT).show();
        }
    }

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
