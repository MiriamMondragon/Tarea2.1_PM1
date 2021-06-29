package com.example.app01sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.app01sqlite.transacciones.Transacciones;

public class SQLiteConexion extends SQLiteOpenHelper {

    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Transacciones.CreateTablePersonas);
        db.execSQL(Transacciones.CreateTableImagenes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Transacciones.DropeTablePersonas);
        db.execSQL(Transacciones.DropeTableImagenes);
        onCreate(db);
    }

    public Cursor getAll() {
        return(getReadableDatabase().rawQuery("SELECT blobImagen FROM imagenes",null));
    }
    public void insert(byte[] bytes)
    {
        ContentValues cv = new ContentValues();

        cv.put(Transacciones.blobImagen,bytes);
        Log.e("inserted", "inserted");
        getWritableDatabase().insert(Transacciones.tablaImagenes,Transacciones.blobImagen,cv);

    }
    public byte[] getImage(Cursor c)
    {
        return(c.getBlob(1));
    }

}
