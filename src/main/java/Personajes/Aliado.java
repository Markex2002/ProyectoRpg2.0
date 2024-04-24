package Personajes;

import java.util.Comparator;

public class Aliado extends Personaje{
    //ATRIBUTOS


    //CONSTRUCTOR
    public Aliado(String nombre, int max_ps, int max_pm, int ataque, int magia, int armadura, int velocidad) {
        super(nombre, max_ps, max_pm, ataque, magia, armadura, velocidad);
    }


    //METODOS//
    //---       MENUS       ---//
    public String menuCombate(){
        String menu = "";

        menu += "(1)Atacar\t(3)Defender";
        menu += "\n(2)Habilidades\t(4)Objetos";

        return menu;
    }



    //---       METODOS DE COMBATE      ---//
    //Método para calcular el daño inflingido
    public void ataqueNormal(Enemigo enemigo){
        //Calculamos el daño infligido
        int totalDamage = ataque - enemigo.armadura;
        //Nos aseguramos de que el daño no sea menor que 0, porque entonces el enemigo se cura xD
        if (totalDamage <= 0){
            totalDamage = 0;
        }

        //Atacamos al enemigo y comprobamos si muere
        enemigo.setPs(enemigo.getPs() - totalDamage);
        System.out.println("\n" + enemigo.nombre + " recibe " + totalDamage + " de daño");

        if (enemigo.getPs() <= 0){
            enemigo.esta_muerto = true;
            System.out.println(enemigo.nombre + " ha sido derrotado\n");
        }
    }
}