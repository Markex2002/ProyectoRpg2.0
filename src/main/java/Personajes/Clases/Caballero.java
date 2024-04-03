package Personajes.Clases;

import Personajes.Personaje;

public class Caballero extends Personaje{
    public Caballero(String nombre, int max_ps, int max_pm, int ataque, int magia, int armadura) {
        super(nombre, max_ps, max_pm, ataque, magia, armadura);
    }
    //ATRIBUTOS

    //CONSTRUCTOR

    //METODOS
    @Override
    public int ataqueNormal() {
        double variable = (Math.random() * (1.20 - 0.80) + 0.80);
        int totalDamage = (int)(getAtaque() * variable);
        System.out.println("\n" + getNombre() + " inflingio " + totalDamage + " de daño\n");
        return totalDamage;
    }

    @Override
    public int recibirAtaque(int damageReceived) {



        return 0;
    }
}