package com.example.listacompra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {
    // Declarar ArrayList y RecyclerView
    private ArrayList<Productos> listaProductos;
    private RecyclerView rvLista;

    private final BBDD_Helper helper = new BBDD_Helper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        // Inicializar RecyclerView
        rvLista = findViewById(R.id.rvLista);
        //Establecer layout del RecyclerView
        rvLista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // Construir ArrayList
        listaProductos = new ArrayList<>();
        // Llamar al metodo buscar que busca en BD
        buscar();
        // Crear el adaptador pasandole como parametro el ArrayList
        AdapterDatos adapter = new AdapterDatos(listaProductos);
        // Asignar al RecyclerView el adaptador
        rvLista.setAdapter(adapter);
    }

    // Metodo para buscar en la BD
    public void buscar() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] selectionArgs = {};

        Productos productos = null;

        Cursor c = db.rawQuery(
                "SELECT * FROM productosCompra",
                selectionArgs
        );

        while (c.moveToNext()) { // Mover el cursor a la siguiente fila
            productos = new Productos();
            productos.setId_producto(c.getInt(0));
            productos.setCantidad(c.getInt(1));
            productos.setNombre(c.getString(2));
            productos.setPrecio_unitario(c.getFloat(3));

            listaProductos.add(productos);

            // Insertar linea que separa cada item
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.recyclerview_linea));
            rvLista.addItemDecoration(dividerItemDecoration);
        }
    }

    // Metodo para volver a la activity principal
    public void volver(View vista) {
        // Método finish
        finish();
    }

}
