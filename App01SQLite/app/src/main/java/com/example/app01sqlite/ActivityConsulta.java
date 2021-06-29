package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app01sqlite.transacciones.Transacciones;

public class ActivityConsulta extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id, txtNombres, txtApellidos, txtEdad, txtCorreo, txtDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        //Llamar conexion
        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);

        Button btnBuscar = (Button) findViewById(R.id.btnBuscar);
        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);
        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);

        id = (EditText) findViewById(R.id.txtIdBuscar);
        txtNombres = (EditText) findViewById(R.id.txtNombresBuscados);
        txtApellidos =  (EditText) findViewById(R.id.txtApellidosBuscados);
        txtEdad =  (EditText) findViewById(R.id.txtEdadBuscados);
        txtCorreo = (EditText) findViewById(R.id.txtCorreoBuscados);
        txtDireccion = (EditText) findViewById(R.id.txtDireccionBuscados);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });

    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()}; //Parametro de Busqueda

        db.delete(Transacciones.tablaPersonas, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato Eliminado", Toast.LENGTH_LONG).show();
        ClearScreen();
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()}; //Parametro de Busqueda

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, txtNombres.getText().toString());
        valores.put(Transacciones.apellidos, txtApellidos.getText().toString());
        valores.put(Transacciones.edad, txtEdad.getText().toString());
        valores.put(Transacciones.correo, txtCorreo.getText().toString());
        valores.put(Transacciones.direccion, txtDireccion.getText().toString());

        db.update(Transacciones.tablaPersonas, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato Actualizado", Toast.LENGTH_LONG).show();
        ClearScreen();
    }

    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()}; //Parametro de Busqueda
        String [] fields = {Transacciones.nombres, Transacciones.apellidos, Transacciones.edad, Transacciones.correo, Transacciones.direccion};
        String whereCon = Transacciones.id + "=?";

        try {
            Cursor cData = db.query(Transacciones.tablaPersonas, fields, whereCon, params, null, null, null, null);
            cData.moveToFirst();

            txtNombres.setText(cData.getString(0));
            txtApellidos.setText(cData.getString(1));
            txtEdad.setText(cData.getString(2));
            txtCorreo.setText(cData.getString(3));
            txtDireccion.setText(cData.getString(4));

            Toast.makeText(getApplicationContext(), "Consultado con exito", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            ClearScreen();
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
        }
    }

    private void ClearScreen() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
    }
}