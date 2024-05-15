package Personajes;

import Inventario.Bolsa;

import java.util.List;
import java.util.Scanner;

public class Aliado extends Personaje{
    //ATRIBUTOS
    private int exp;
    private int xpGoal;
    private int level;

    //CONSTRUCTOR
    public Aliado(String nombre, int max_ps, int max_pm, int ataque, int magia, int armadura, int velocidad) {
        super(nombre, max_ps, max_pm, ataque, magia, armadura, velocidad);
        xpGoal = 100;
        level = 1;
    }


    //METODOS//
    //---       MENUS       ---//
    public String menuCombate(){
        String menu = "";

        menu += "(1)Atacar\t(3)Defender";
        menu += "\n(2)Habilidades\t(4)Objetos";

        return menu;
    }

    //Metodo que permitira a los aliados usar Objetos en Batalla
    public boolean usarObjeto(List<Aliado> grupoAliado, Bolsa bolsa, Scanner sc){
        boolean eleccionTomada;
        boolean seHaActuado = true;

        //Hasta que no hayamos usado el objeto, o le hayamos dado a Salir, se mantiene el bucle
        do {
            int numObjeto;
            //Mostramos las opciones y Objetos y no salimos hasta que elijamos
            do{
                System.out.println("\n(-1) Salir");
                System.out.println(bolsa.toString());
                numObjeto = sc.nextInt();

            } while ((numObjeto < -1) || (numObjeto > bolsa.getInventario().size() - 1));


            //Elegimos a que aliado que aliado vamos a dar el objeto
            if (numObjeto >= 0){
                System.out.println("¿Con que Aliado quieres usarlo?");
                for (int i = 0; i < grupoAliado.size(); i++) {
                    System.out.println("(" + i + ")" + grupoAliado.get(i).getNombre()
                            + ": " + grupoAliado.get(i).getPs() + " / "
                            + grupoAliado.get(i).getMax_ps() + "ps"
                    );
                }

                int numAliado = sc.nextInt();

                //Usamos el objeto o No, dependiendo de si se cumplen las condiciones
                eleccionTomada = bolsa.getInventario().get(numObjeto).usarObjeto(grupoAliado.get(numAliado));
            }else{
                //Condicion de salir, No hemos actuado, pero salimos del bucle
                eleccionTomada = true;
                seHaActuado = false;
            }
        } while (!eleccionTomada);

        return seHaActuado;
    }



    //---       METODOS DE COMBATE      ---//
    //Método para calcular el daño inflingido
    public void ataqueNormal(Enemigo enemigo){
        //Calculamos el daño infligido
        double randomValue = ((Math.random() * (110 - 90) + 90)) / 100;
        int totalDamage =(int)((ataque/2 - enemigo.armadura/4) * randomValue);
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