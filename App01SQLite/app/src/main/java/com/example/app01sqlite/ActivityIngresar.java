package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app01sqlite.transacciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity {

    EditText txtNombres, txtApellidos, txtEdad, txtCorreo, txtDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        Button btn = (Button) findViewById(R.id.button);
        txtNombres = (EditText) findViewById(R.id.txtNombres);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarPersona();
            }
        });

    }

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txtNombres.getText().toString());
        valores.put(Transacciones.apellidos, txtApellidos.getText().toString());
        valores.put(Transacciones.edad, txtEdad.getText().toString());
        valores.put(Transacciones.correo, txtCorreo.getText().toString());
        valores.put(Transacciones.direccion, txtDireccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablaPersonas, Transacciones.id, valores);
        Toast.makeText(getApplicationContext(), "Registro Insertado: " + resultado.toString(), Toast.LENGTH_LONG).show();
        db.close();
        ClearScreen();
    }

    private void ClearScreen() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");

    }
}