package com.example.app01sqlite.transacciones;

public class Transacciones {
    /* Tablas */
    public static final String tablaPersonas = "personas";
    /* Campos */
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    public static final String tablaImagenes = "imagenes";
    public static final String idImagen = "idImagen";
    public static final String blobImagen = "blobImagen";

    /* CRUD */
    public static final String CreateTablePersonas =
            "CREATE TABLE personas(id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, edad INTEGER, correo TEXT, direccion TEXT)";
    public static final String DropeTablePersonas =
            "DROPE TABLE IF EXISTS personas";

    public static final String CreateTableImagenes =
            "CREATE TABLE imagenes(idImagen INTEGER PRIMARY KEY AUTOINCREMENT, blobImagen BLOB)";
    public static final String DropeTableImagenes =
            "DROPE TABLE IF EXISTS imagenes";

    /* Base de Datos */
    public static final String NameDataBase = "DBCurso";
}
