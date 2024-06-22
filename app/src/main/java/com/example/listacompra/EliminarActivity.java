package com.example.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class EliminarActivity extends AppCompatActivity {
    // Declarar componentes
    private TextView tvResultadoEli;
    private Spinner sEliminar; // Declarar Spìnner

    // Crear instancia para definir las acciones a realizar sobre la BD
    private final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        // Inicializar componentes
        tvResultadoEli = findViewById(R.id.tvResultadoEli);
        sEliminar = findViewById(R.id.sEliminar); // Inicializar el Spinner

        // -- CARGAR SPINNER --
        SQLiteDatabase db = helper.getReadableDatabase();

        // Determinar columnas a devolver
        String[] columnasDevolver = {
                Estructura_BBDD.NOMBRE_COLUMNA3
        };

        // Consulta a realizar
        Cursor c = db.query(
                Estructura_BBDD.TABLE_NAME,
                columnasDevolver,
                null,
                null,
                null,
                null,
                null
        );

        // Mover cursor a primer resultado
        c.moveToFirst();

        String resultados = "LISTA DE PRODUCTOS";

        // Recorrer resultados
        do {
            String nombre = c.getString(0);

            resultados = resultados + "," + nombre;
        } while (c.moveToNext());

        // Crear Adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, resultados.split(","));

        // Cargar datos en Spinner
        sEliminar.setAdapter(adaptador);

        // -- Fin carga de Spinner --
    }

    // Metodo que comprueba si se ha seleccionado el nombre del producto a eliminar
    private boolean validar() {
        boolean valido = true;
        if (sEliminar.getSelectedItemPosition() == 0) { // Si no selecciona el producto
            valido = false;
            // Mensaje de feedback
            // Toast.makeText(this, "¡ERROR! Debe seleccionar un producto.", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "¡ERROR! Debe seleccionar un producto.", Toast.LENGTH_SHORT).show();
        }
        return valido;
    }

    // Metodo para borrar en la BD
    public void borrar(View vista) {
        if (validar()) {
            SQLiteDatabase db = helper.getWritableDatabase();

            String selection = Estructura_BBDD.NOMBRE_COLUMNA3 + " LIKE ?";
            String[] selectionArgs = {sEliminar.getSelectedItem().toString()};
            db.delete(Estructura_BBDD.TABLE_NAME, selection, selectionArgs);

            Toast.makeText(getApplicationContext(), "Se borró el producto.", Toast.LENGTH_SHORT).show();
        }
    }

    public void listar(View vista) {
        // Abrir conexión con BBDD
        SQLiteDatabase db = helper.getReadableDatabase();

        // Determinar columnas a devolver
        String[] columnasDevolver = {
                Estructura_BBDD.NOMBRE_COLUMNA1,
                Estructura_BBDD.NOMBRE_COLUMNA2,
                Estructura_BBDD.NOMBRE_COLUMNA3,
                Estructura_BBDD.NOMBRE_COLUMNA4
        };

        // CONSULTA
        try {
            // Consulta a realizar
            Cursor c = db.query(
                    Estructura_BBDD.TABLE_NAME,
                    columnasDevolver,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            // Mover cursor a primer resultado
            c.moveToFirst();

            String resultados = "";

            // Recorrer resultados
            do {
                int Id_producto = c.getInt(0);
                int cantidad = c.getInt(1);
                String nombre = c.getString(2);
                Float precio_unitario = c.getFloat(3);

                resultados = resultados + Id_producto + "º- " + cantidad + " unidades de " + nombre + " a "
                        + precio_unitario + " €" + "\n";
            } while (c.moveToNext());

            // Mostrar resultados
            tvResultadoEli.setText(resultados);

        } catch (Exception e) {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_LONG).show();
        }
    }

    // Metodo para volver a la activity principal
    public void volver(View vista) {
        // Método finish
        finish();
    }

}