package Inventario.ObjetosCombate;

import Inventario.Objeto;
import Personajes.Personaje;

public class SuperPocion extends Objeto {
    //Atributos
    private final int psRestaurados = 180;

    //Constructor
    public SuperPocion(int cantidad) {
        super(cantidad);
        id = 2;
        nombre = "SuperPoción";
    }

    @Override
    public String descripcionObjeto() {
        String descripcion = "";
        descripcion += "Poción hecha con una mezcla de lagrimas de niños y unicornios.";
        descripcion += "\nRestaura " + psRestaurados + " ps";

        return descripcion;
    }

    @Override
    public void usarObjeto(Personaje personaje) {
        personaje.setPs(personaje.getPs() + psRestaurados);
    }
}