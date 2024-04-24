package Inventario.ObjetosCombate;

import Inventario.Objeto;
import Personajes.Personaje;

public class Pocion extends Objeto {
    //Atributos
    private final int psRestaurados = 50;

    //Constructor
    public Pocion(int cantidad) {
        super(cantidad);
        id = 1;
        nombre = "Poción";
    }

    @Override
    public String descripcionObjeto() {
        String descripcion = "";
        descripcion += "Poción hecha con extracto purificado de Slime.";
        descripcion += "\nRestaura " + psRestaurados + " ps";

        return descripcion;
    }

    @Override
    public void usarObjeto(Personaje personaje) {
        personaje.setPs(personaje.getPs() + psRestaurados);
    }
}