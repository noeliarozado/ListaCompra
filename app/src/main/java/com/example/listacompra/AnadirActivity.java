package com.example.listacompra;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnadirActivity extends AppCompatActivity {
    // Declarar componentes
    private EditText etCantidadAnadir, etNombreAnadir, etPrecioAnadir;

    // Crear instancia para definir las acciones a realizar sobre la BD
    private final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        // Inicializar componentes
        etCantidadAnadir = findViewById(R.id.etCantidadAnadir);
        etNombreAnadir = findViewById(R.id.etNombreAnadir);
        etPrecioAnadir = findViewById(R.id.etPrecioAnadir);
    }

    // Metodo que comprueba si se ha introducido la cantidad, el nombre del producto y el precio
    private boolean validar(String cantidad, String producto, String precio) {
        boolean valido = true;
        if (cantidad.length() == 0 || producto.length() == 0 || precio.length() == 0) { // Si no introduce algun campo
            valido = false;
            // Mensaje de feedback
            // Toast.makeText(this, "¡ERROR! Debe rellenar todos los campos.", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "¡ERROR! Debe rellenar todos los campos.", Toast.LENGTH_SHORT).show();
        }
        return valido;
    }

    // Mrtodo para insertar en la BD
    public void insertar(View vista) {
        if (validar(etCantidadAnadir.getText().toString(), etNombreAnadir.getText().toString(), etPrecioAnadir.getText().toString())) {
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Estructura_BBDD.NOMBRE_COLUMNA2, etCantidadAnadir.getText().toString());
            values.put(Estructura_BBDD.NOMBRE_COLUMNA3, etNombreAnadir.getText().toString());
            values.put(Estructura_BBDD.NOMBRE_COLUMNA4, etPrecioAnadir.getText().toString());

            long newRowId = db.insert(Estructura_BBDD.TABLE_NAME, null, values);

            Toast.makeText(getApplicationContext(), "Se añadió el producto.", Toast.LENGTH_SHORT).show();

            // Vaciar campos
            etCantidadAnadir.setText("");
            etNombreAnadir.setText("");
            etPrecioAnadir.setText("");
        }
    }

    // Metodo para volver a la activity principal
    public void volver(View vista) {
        // Metodo finish
        finish();
    }

}