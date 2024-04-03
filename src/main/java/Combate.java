import Personajes.Enemigo;
import Personajes.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Combate {
    //ATRIBUTOS
    private List<Personaje> grupo = new ArrayList<>();
    private List<Enemigo> grupoEnemigo = new ArrayList<>();


    //CONSTRUCTOR
    public Combate(List<Personaje> grupo, List<Enemigo> grupoEnemigo) {
        this.grupo = grupo;
        this.grupoEnemigo = grupoEnemigo;
    }

    public void comenzarCombate(){
        do {
            System.out.println("El combate Continua");









        } while (comprobarDerrota(grupo));
















    }



    //----CONDICIONES----//
    public boolean comprobarDerrota(List<Personaje> grupo){
        boolean derrota = false;
        int contadorMuertes = 0;

        //Contamos cuantos miembros del grupo han muerto
        for (Personaje personaje : grupo){
            if (personaje.isEsta_muerto()){
                contadorMuertes++;
            }
        }

        System.out.println(grupo.size());
        System.out.println(contadorMuertes);

        //Vemos si todos han muerto
        if (contadorMuertes == grupo.size()){
            derrota = true;
        }

        return derrota;
    }
    public boolean comprobarVictoria(){
        return true;
    }
}