package com.example.listacompra;

// Clase Estructura_BBDD
public class Estructura_BBDD {
    // Nombre tabla BD
    public static final String TABLE_NAME = "productosCompra";
    // Campos tabla
    public static final String NOMBRE_COLUMNA1 = "id_producto";
    public static final String NOMBRE_COLUMNA2 = "cantidad";
    public static final String NOMBRE_COLUMNA3 = "nombre";
    public static final String NOMBRE_COLUMNA4 = "precio_unitario";
    // Crear tabla
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Estructura_BBDD.TABLE_NAME + " (" +
                    Estructura_BBDD.NOMBRE_COLUMNA1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Estructura_BBDD.NOMBRE_COLUMNA2 + " INTEGER" + "," +
                    Estructura_BBDD.NOMBRE_COLUMNA3 + " TEXT" + "," +
                    Estructura_BBDD.NOMBRE_COLUMNA4 + " DECIMAL" + ");";

    // Borrar tabla
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_BBDD.TABLE_NAME;
}

// RESETEAR AUTOINCREMENT > Borrar todos los datos en DB Browser
// DELETE FROM productosCompra;
// DELETE FROM sqlite_sequence WHERE name = 'productosCompra';