package com.example.listacompra;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Clase AdapterDatos
public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {
    // Declarar ArrayList
    private ArrayList<Productos> listaProductos;

    // Constructor AdapterDatos
    public AdapterDatos(ArrayList<Productos> listaProductos) {
        this.listaProductos = listaProductos; // Asignar ArrayList recibido como parametro
    }

    // Crear contenedor de datos relacionandolo con el item_list
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(vista);
    }

    // Enlazar el contenedor con las distintas posiciones del ArrayList
    public void onBindViewHolder(AdapterDatos.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaProductos.get(position));
    }

    // Devolver el tamaño del ArrayList
    public int getItemCount() {
        return listaProductos.size();
    }

    // Clase ViewHolderDatos
    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        // Declarar TextView
        private TextView tvIdProducto, tvCantidad, tvNombre, tvPrecioUnitario;

        // Constructor ViewHolderDatos
        public ViewHolderDatos(View itemView) {
            super(itemView);
            // Inicializar TextView
            tvIdProducto = itemView.findViewById(R.id.tvIdProducto);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecioUnitario = itemView.findViewById(R.id.tvPrecioUnitario);
        }

        // Asignar los datos a los componentes
        public void asignarDatos(Productos p) {
            tvIdProducto.setText("" + p.getId_producto());
            tvCantidad.setText("Cantidad: " + p.getCantidad());
            tvNombre.setText("Nombre: " + p.getNombre());
            tvPrecioUnitario.setText("Precio unitario: " + p.getPrecio_unitario() + " €");
        }

    }
}

