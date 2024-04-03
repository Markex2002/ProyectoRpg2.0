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
        Guerrero guerrero = new Guerrero("Marco", 100, 20, 80, 20, 60);
        Mago mago = new Mago("Cali", 100, 20, 80, 20, 60);
        Arquero arquero = new Arquero("Legolas", 100, 20, 80, 20, 60);
        Caballero caballero = new Caballero("Edea", 100, 20, 80, 20, 60);


        //ENEMIGOS
        DuendiLemon duendiLemon = new DuendiLemon("DuendiLemon A", 300, 100, 100, 100, 100);


        //CREACION DEL GRUPO
        List<Personaje> grupo = new ArrayList<>();
        grupo.add(guerrero);
        grupo.add(mago);
        grupo.add(arquero);
        grupo.add(caballero);


        //CREACION DE GRUPOS ENEMIGOS
        List<Enemigo> grupoEnemigo = new ArrayList<>();



        //COMBATES
        Combate combate = new Combate(grupo, grupoEnemigo);





    }
}