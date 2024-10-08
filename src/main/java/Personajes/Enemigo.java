package Personajes;

import java.util.List;

public class Enemigo extends Personaje {
    //ATRIBUTOS

    //CONSTRUCTOR
    public Enemigo(String nombre, int max_ps, int max_pm, int ataque, int magia, int armadura, int velocidad) {
        super(nombre, max_ps, max_pm, ataque, magia, armadura, velocidad);
    }

    public Enemigo(){};




    //GETTERS AND SETTERS





    //METODOS//
    //---       METODOS DE COMBATE      ---//
    public void ataqueNormal(List<Aliado> grupoAliados){
        //Empezamos eligiendo a qué personaje vamos a atacar
        Aliado aliado = elegirObjetivoAlAzar(grupoAliados);

        //Infligimos daño al personaje /////EN UN FUTURO CREAR UNA PROBABILIDAD DE ESQUIVE, FIJA O DINÁMICA
        double randomValue = ((Math.random() * (110 - 90) + 90)) / 100;
        int damageBase =(int)((ataque/2 - aliado.armadura/4) * randomValue);
        int damageReceived;
        //Que no sea menor que 0
        if (damageBase <= 0){
            damageBase = 0;
        }

        //Nos fijamos en si el personaje se está defendiendo
        if (aliado.isSe_defiende()){
            aliado.setPs(aliado.getPs() - damageBase/2);
            damageReceived = damageBase/2;
        } else {
            aliado.setPs(aliado.getPs() - damageBase);
            damageReceived = damageBase;
        }

        System.out.println("\n" + nombre + " ataca a " + aliado.getNombre() + "!");
        System.out.println(aliado.getNombre() + " recibe " + damageReceived + " de daño!");
        if (aliado.esta_muerto) {
            System.out.println(aliado.nombre + " ha caido!");
        }
    }

    //Metodo para atacar a un miembro aleatorio del grupo Aliado
    public Aliado elegirObjetivoAlAzar(List<Aliado> grupoAliados){
        //Hay que asegurarse de que no ataquen a un muerto
        boolean haElegido = false;
        int eleccion = (int)(Math.random() * (grupoAliados.size()) + 0);

        //Bucle hasta que se eliga al personaje
        do {
            //Miramos si esta muerto, si no, elegimos uno nuevo
            if (!grupoAliados.get(eleccion).isEsta_muerto()){
                haElegido = true;
            } else {
                eleccion = (int)(Math.random() * (grupoAliados.size()) + 0);
            }
        }while (!haElegido);

        return grupoAliados.get(eleccion);
    }

    @Override
    public int compareTo(Personaje o) {
        return 0;
    }
}