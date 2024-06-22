package com.example.listacompra;

public class Productos {
    // Atributos
    private int id_producto;
    private int cantidad;
    private String nombre;
    private float precio_unitario;

    // Constructor por defecto
    public Productos() {

    }

    // Constructor con parametros
    // Generar constructor > Click derecho (o Menu > Code) > Generate
    public Productos(int id_producto, int cantidad, String nombre, float precio_unitario) {
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio_unitario = precio_unitario;
    }

    // Getters y setters
    // Generar getters y setters > Click derecho (o Menu > Code) > Generate
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}
