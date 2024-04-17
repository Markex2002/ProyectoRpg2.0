import Personajes.Clases.Arquero;
import Personajes.Clases.Caballero;
import Personajes.Clases.Guerrero;
import Personajes.Clases.Mago;
import Personajes.Enemigo;
import Personajes.Enemigos.DuendiLemon;
import Personajes.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){

        //CREACION DE PERSONAJES
        Guerrero guerrero = new Guerrero("Marco", 100, 40, 60, 20, 60);
        Mago mago = new Mago("Cali", 100, 20, 20, 20, 60);
        Arquero arquero = new Arquero("Legolas", 100, 30, 80, 20, 60);
        Caballero caballero = new Caballero("Edea", 100, 40, 80, 20, 60);


        //ENEMIGOS
        DuendiLemon duendiLemon1 = new DuendiLemon("DuendiLemon A", 100, 20, 20, 20, 20);
        DuendiLemon duendiLemon2 = new DuendiLemon("DuendiLemon B", 100, 20, 20, 20, 20);
        DuendiLemon duendiLemon3 = new DuendiLemon("DuendiLemon C", 100, 20, 20, 20, 20);


        //CREACION DEL GRUPO
        List<Personaje> grupo = new ArrayList<>();
        grupo.add(guerrero);
        grupo.add(mago);
        grupo.add(arquero);
        grupo.add(caballero);


        //CREACION DE GRUPOS ENEMIGOS
        List<Enemigo> grupoEnemigo = new ArrayList<>();
        grupoEnemigo.add(duendiLemon1);
        grupoEnemigo.add(duendiLemon2);
        grupoEnemigo.add(duendiLemon3);


        //COMBATES
        Combate combate = new Combate(grupo, grupoEnemigo);
        combate.comenzarCombate();




    }
}