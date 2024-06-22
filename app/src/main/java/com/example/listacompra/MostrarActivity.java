package com.example.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarActivity extends AppCompatActivity {
    // Declarar componentes
    private TextView tvNumProductos, tvCoste;

    // Crear instancia para definir las acciones a realizar sobre la BD
    private final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        // Inicializar componentes
        tvNumProductos = findViewById(R.id.tvNumProductos);
        tvCoste = findViewById(R.id.tvCoste);
    }

    // Metodo para buscar en la BD
    @SuppressLint("SuspiciousIndentation")
    public void buscar(View vista) {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] projection = {
                Estructura_BBDD.NOMBRE_COLUMNA1,
                Estructura_BBDD.NOMBRE_COLUMNA2,
                Estructura_BBDD.NOMBRE_COLUMNA3,
                Estructura_BBDD.NOMBRE_COLUMNA4
        };

        String[] selectionArgs = {};

        // Total de productos a comprar
        Cursor c = db.rawQuery(
                "SELECT SUM(cantidad) FROM productosCompra;",
                selectionArgs
        );

        c.moveToFirst();

        tvNumProductos.setText(c.getString(0) + " productos");

        // Coste total compra
        c = db.rawQuery(
                "SELECT SUM(cantidad * precio_unitario) FROM productosCompra;",
                selectionArgs
        );

        c.moveToFirst();

        tvCoste.setText(c.getString(0)  + " euros");
    }

    // Metodo para volver a la activity principal
    public void volver(View vista) {
        // Método finish
        finish();
    }

}