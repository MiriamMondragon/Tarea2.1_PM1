package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.app01sqlite.tablas.Personas;
import com.example.app01sqlite.transacciones.Transacciones;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    SQLiteConexion conexion;
    Spinner cmbPersonas;
    EditText txtNombre, txtApellidos, txtCorreo;
    ArrayList<String> listaPersonas;
    ArrayList<Personas> personas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        cmbPersonas = (Spinner) findViewById(R.id.cmbPersonas);
        txtNombre = (EditText) findViewById(R.id.txtNombresCmb);
        txtApellidos = (EditText) findViewById(R.id.txtApellidosCmb);
        txtCorreo = (EditText) findViewById(R.id.txtCorreoCmb);

        ObtenerListaPersonas();
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaPersonas);
        cmbPersonas.setAdapter(adp);

        cmbPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                txtNombre.setText(personas.get(position).getNombres());
                txtApellidos.setText(personas.get(position).getApellidos());
                txtCorreo.setText(personas.get(position).getCorreo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas persona = null;

        personas = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaPersonas, null);

        while (cursor.moveToNext()){
            persona = new Personas();
            persona.setId(cursor.getInt(0));
            persona.setNombres(cursor.getString(1));
            persona.setApellidos(cursor.getString(2));
            persona.setEdad(cursor.getInt(3));
            persona.setCorreo(cursor.getString(4));
            persona.setDireccion(cursor.getString(5));

            personas.add(persona);
        }

        fillComb();
        cursor.close();
    }

    private void fillComb() {
        listaPersonas = new ArrayList<String>();
        for(int i = 0; i < personas.size(); i++){
            listaPersonas.add(personas.get(i).getId() + "  |  "
                    + personas.get(i).getNombres() + " "
                    + personas.get(i).getApellidos());
        }
    }
}