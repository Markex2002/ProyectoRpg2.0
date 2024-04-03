import Personajes.Clases.Arquero;
import Personajes.Clases.Caballero;
import Personajes.Clases.Guerrero;
import Personajes.Clases.Mago;
import Personajes.Enemigos.DuendiLemon;

public class Main {
    public static void main(String[] args){
        //CREACION DE PERSONAJES
        Guerrero guerrero = new Guerrero("Marco", 100, 20, 80, 20, 60);
        Mago mago = new Mago("Cali", 100, 20, 80, 20, 60);
        Arquero arquero = new Arquero("Legolas", 100, 20, 80, 20, 60);
        Caballero caballero = new Caballero("Edea", 100, 20, 80, 20, 60);


        //ENEMIGOS
        DuendiLemon duendiLemon = new DuendiLemon("DuendiLemon A", 300, 100, 100, 100, 100);


        //MOVIDAS DEL MAPA



        //COMBATES



        duendiLemon.setPs(duendiLemon.getPs() - guerrero.ataqueNormal());
        System.out.println(duendiLemon);
        duendiLemon.setPs(duendiLemon.getPs() - guerrero.ataqueNormal());
        System.out.println(duendiLemon);





    }
}