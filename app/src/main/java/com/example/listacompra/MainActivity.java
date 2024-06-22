package com.example.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Metodo para entrar en Listar productos
    public void entrarListar(View vista) {
        // Conectar esta activity con listar productos
        Intent i = new Intent(this, ListarActivity.class);
        startActivity(i);
    }

    // Metodo para entrar en Añadir producto
    public void entrarAnadir(View vista) {
        // Conectar esta activity con añadir productos
        Intent i = new Intent(this, AnadirActivity.class);
        startActivity(i);
    }

    // Metodo para entrar en Actualizar producto
    public void entrarActualizar(View vista) {
        // Conectar esta activity con actualizar producto
        Intent i = new Intent(this, ActualizarActivity.class);
        startActivity(i);
    }

    // Metodo para entrar en Eliminar producto
    public void entrarEliminar(View vista) {
        // Conectar esta activity con eliminar producto
        Intent i = new Intent(this, EliminarActivity.class);
        startActivity(i);
    }

    // Metodo para entrar en Mostrar informacion
    public void entrarMostrar(View vista) {
        // Conectar esta activity con mostrar información
        Intent i = new Intent(this, MostrarActivity.class);
        startActivity(i);
    }

    // Metodo para salir de la aplicacion con dialogo de confirmacion de salida
    public void salir(View vista) {
        new AlertDialog.Builder(this)
                // .setTitle("Salir")
                .setTitle("Salir")
                // .setMessage("¿Desea salir de la App?")
                .setMessage("¿Desea salir de la App?")
                // .setPositiveButton("Sí", new DialogInterface.OnClickListener(){
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                // .setNegativeButton("No", null)
                .setNegativeButton("No", null)
                .show();
    }
}