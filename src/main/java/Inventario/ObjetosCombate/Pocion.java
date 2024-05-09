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
    public boolean usarObjeto(Personaje personaje) {
        boolean seHaUsado = false;

        //Comprobamos que se pueda usar la pocion
        if ((personaje.isEsta_muerto()) || (personaje.getPs() == personaje.getMax_ps())){
            System.out.println("Esto no tendría sentido");
        } else {
            personaje.setPs(personaje.getPs() + psRestaurados);
            seHaUsado = true;
        }
        return seHaUsado;
    }
}