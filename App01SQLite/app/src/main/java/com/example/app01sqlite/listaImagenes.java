package com.example.app01sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.example.app01sqlite.transacciones.Transacciones;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class listaImagenes extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagenes);

        GridView simpleGridView = (GridView) findViewById(R.id.simpleGridView);

        SQLiteConexion help = new SQLiteConexion(this, Transacciones.NameDataBase, null, 1);
        ArrayList<Item> listaImagenes = new ArrayList<>();
        Cursor c= help.getAll();
        int i=0;
        if(c.getCount()>0)
        {
            Bitmap[]  array = new Bitmap[c.getCount()];
            c.moveToFirst();
            while(c.isAfterLast()==false)
            {

                byte[] bytes=c.getBlob(c.getColumnIndex(Transacciones.blobImagen));

                Bitmap ude = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                Item item = new Item(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                listaImagenes.add(item);
                c.moveToNext();
                i++;
            }

            Adaptador myAdapter=new Adaptador(this,R.layout.grid_view_items,listaImagenes);
            simpleGridView.setAdapter(myAdapter);
        }
    }
}