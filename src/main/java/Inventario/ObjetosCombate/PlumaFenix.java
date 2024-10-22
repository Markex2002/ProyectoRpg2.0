package Inventario.ObjetosCombate;

import Inventario.Objeto;
import Personajes.Personaje;

public class PlumaFenix extends Objeto {

    //Constructor
    public PlumaFenix(int cantidad) {
        super(cantidad);
        id = 3;
        nombre = "Pluma de Fenix";
    }

    @Override
    public String descripcionObjeto() {
        String descripcion = "";
        descripcion += "Según dicen las leyendas, el poseedor de esta pluma";
        descripcion += "\nresucitara cual ave Fenix, con la mitad de sus Ps claro ";

        return descripcion;
    }

    @Override
    public boolean usarObjeto(Personaje personaje) {
        boolean seHaUsado = false;

        //Comprobamos que se pueda usar la Pluma de Fenix
        if (personaje.isEsta_muerto()){
            personaje.setPs(personaje.getMax_ps()/2);
            personaje.setEsta_muerto(false);
            seHaUsado = true;
            cantidad--;
            System.out.println(personaje.getNombre() + " ha vuelto al combate!\n");
        } else {
            System.out.println("\nEsto no tendría sentido");
        }
        return seHaUsado;
    }
}