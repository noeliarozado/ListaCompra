package com.example.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActualizarActivity extends AppCompatActivity {
    // Declarar componentes
    private EditText etCantidadActualizar, etPrecioActualizar;
    private TextView tvResultadoAct;
    private Spinner sActualizar; // Declarar Spìnner

    // Crear instancia para definir las acciones a realizar sobre la BD
    private final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        // Inicializar componentes
        etCantidadActualizar = findViewById(R.id.etCantidadActualizar);
        etPrecioActualizar = findViewById(R.id.etPrecioActualizar);
        tvResultadoAct = findViewById(R.id.tvResultadoAct);
        sActualizar = findViewById(R.id.sActualizar); // Inicializar el Spinner

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
        sActualizar.setAdapter(adaptador);

        // -- Fin carga de Spinner --
    }

    // Metodo que comprueba si se ha seleccionado el producto y si se ha introducido la cantidad y/o el precio
    private boolean validar(String cantidad, String precio) {
        boolean valido = true;
        if (sActualizar.getSelectedItemPosition() == 0) { // Si no se selecciona el producto
            valido = false;
            // Mensaje de feedback
            // Toast.makeText(this, "¡ERROR! Debe seleccionar un producto.", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "¡ERROR! Debe seleccionar un producto.", Toast.LENGTH_SHORT).show();
        } else if (cantidad.length() == 0 && precio.length() == 0) { //Si no se introduce la cantidad o el precio
            valido = false;
            // Mensaje de feedback
            // Toast.makeText(this, "¡ERROR! Debe introducir la nueva cantidad y/o precio.", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "¡ERROR! Debe introducir la nueva cantidad y/o precio.", Toast.LENGTH_SHORT).show();
        }
        return valido;
    }

    // Metodo para actualizar en la BD
    public void actualizar(View vista) {
        if (validar(etCantidadActualizar.getText().toString(), etPrecioActualizar.getText().toString())) {
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();

            // Si la cantidad esta sin cubrir, actualizar solo el precio
            if (etCantidadActualizar.getText().toString().equals(""))
                values.put(Estructura_BBDD.NOMBRE_COLUMNA4, etPrecioActualizar.getText().toString());
            // Si el precio esta sin cubrir, actualizar solo la cantidad
            if (etPrecioActualizar.getText().toString().equals(""))
                values.put(Estructura_BBDD.NOMBRE_COLUMNA2, etCantidadActualizar.getText().toString());
            // Si cubre la cantidad y el precio
            if (!etCantidadActualizar.getText().toString().equals("") &&
                    !etPrecioActualizar.getText().toString().equals("")) {
                values.put(Estructura_BBDD.NOMBRE_COLUMNA4, etPrecioActualizar.getText().toString());
                values.put(Estructura_BBDD.NOMBRE_COLUMNA2, etCantidadActualizar.getText().toString());
            }

            String selection = Estructura_BBDD.NOMBRE_COLUMNA3 + " LIKE ?";
            String[] selectionArgs = {sActualizar.getSelectedItem().toString()};

            int count = db.update(
                    Estructura_BBDD.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

            Toast.makeText(getApplicationContext(), "Se actualizó el producto. ", Toast.LENGTH_SHORT).show();

            // Vaciar campos
            etCantidadActualizar.setText("");
            etPrecioActualizar.setText("");
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
            tvResultadoAct.setText(resultados);

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