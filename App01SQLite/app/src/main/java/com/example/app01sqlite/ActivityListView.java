package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.app01sqlite.tablas.Personas;
import com.example.app01sqlite.transacciones.Transacciones;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {

    SQLiteConexion conexion;
    ListView listaPersonas;
    ArrayList<Personas> lista;
    ArrayList<String> arregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        listaPersonas = (ListView) findViewById(R.id.listaPersonas);

        ObtenerListaPersonas();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arregloPersonas);
        listaPersonas.setAdapter(adp);

        listaPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "ID: " + lista.get(position).getId();
                informacion += " Nombre: " + lista.get(position).getNombres();
                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_LONG).show();

                Intent compartir = new Intent();
                compartir.setAction(Intent.ACTION_SEND);
                compartir.putExtra(Intent.EXTRA_TEXT, informacion);
                compartir.setType("text/plain");
                
                Intent share = Intent.createChooser(compartir, null);
                startActivity(share);
            }
        });
    }

    private void ObtenerListaPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas persona = null;

        lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.tablaPersonas, null);

        while (cursor.moveToNext()){
            persona = new Personas();
            persona.setId(cursor.getInt(0));
            persona.setNombres(cursor.getString(1));
            persona.setApellidos(cursor.getString(2));
            persona.setEdad(cursor.getInt(3));
            persona.setCorreo(cursor.getString(4));
            persona.setDireccion(cursor.getString(5));

            lista.add(persona);
        }

        fillList();
        cursor.close();
    }

    private void fillList() {
        arregloPersonas = new ArrayList<String>();
        for(int i = 0; i < lista.size(); i++){
            arregloPersonas.add(lista.get(i).getId() + "  |  "
                              + lista.get(i).getNombres() + " "
                              + lista.get(i).getApellidos());
        }
    }
}