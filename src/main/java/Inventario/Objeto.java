package Inventario;

import Personajes.Personaje;

public abstract class Objeto {
    //Atributos
    protected int id;
    protected String nombre;
    protected int cantidad;

    //Constructor
    public Objeto(int cantidad) {
        this.cantidad = cantidad + this.cantidad;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    //Metodos
    public abstract String descripcionObjeto();
    public abstract boolean usarObjeto(Personaje personaje);
}