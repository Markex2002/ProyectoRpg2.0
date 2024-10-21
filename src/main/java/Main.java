import Inventario.Bolsa;
import Inventario.ObjetosCombate.PlumaFenix;
import Inventario.ObjetosCombate.Pocion;
import Inventario.ObjetosCombate.SuperPocion;
import Mazmorra.MazmorraFinal;
import Personajes.Aliado;
import Personajes.Clases.*;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        //CREACION DE BOLSA
        Bolsa bolsa = new Bolsa();

        //INSTANCIAMOS EL OBJETO EN EL MISMO MOMENTO, ASI EVITAMOS CREAR MUCHAS CLASES IGUALES
        bolsa.addObject(new Pocion(1));
        bolsa.addObject(new SuperPocion(2));
        bolsa.addObject(new Pocion(2));
        bolsa.addObject(new PlumaFenix(3));


        //CREACIÓN DE PERSONAJES
        Guerrero guerrero = new Guerrero("Marco", 100, 40, 600, 20, 30, 40);
        Mago mago = new Mago("Cali", 100, 20, 400, 20, 10, 20);
        Arquero arquero = new Arquero("Legolas", 100, 30, 700, 20, 20,60);
        Caballero caballero = new Caballero("Edea", 100, 40, 800, 20, 45,10);


        //CREACION DE ENEMIGOS
        //DuendiLemon duendiLemon1 = new DuendiLemon("DuendiLemon A");
        //DuendiLemon duendiLemon2 = new DuendiLemon("DuendiLemon B");
        //DuendiLemon duendiLemon3 = new DuendiLemon("DuendiLemon C");


        //CREACIÓN DEL GRUPO
        //Este grupo está compuesto por clases que heredan de la clase Personaje
        List<Aliado> grupo = new ArrayList<>();
        grupo.add(guerrero);
        grupo.add(mago);
        grupo.add(arquero);
        grupo.add(caballero);


        //CREACIÓN DE GRUPOS ENEMIGOS
        //Este grupo está compuesto por clases que heredan de Enemigo
        //List<Enemigo> grupoEnemigo1 = new ArrayList<>();
        //grupoEnemigo1.add(duendiLemon1);
        //grupoEnemigo1.add(duendiLemon2);
        //grupoEnemigo1.add(duendiLemon3);


        //COMBATES//
        //Creamos el combate con ambos grupos
        //Combate combate = new Combate(grupo, grupoEnemigo1, bolsa);
        //Comenzamos el combate
        //combate.comenzarCombate();





        //MAZMORRAS//
        //Creamos la mazmorra y metemos a nuestros heroes
        MazmorraFinal mazmorraFinal = new MazmorraFinal(grupo, bolsa);
        mazmorraFinal.watchMap();

        //Jugamos la Mazmorra
        mazmorraFinal.exploreDungeon();




    }
}