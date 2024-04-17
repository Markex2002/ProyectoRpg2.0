import Personajes.Enemigo;
import Personajes.Personaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combate {
    //ATRIBUTOS
    private final List<Personaje> grupo;
    private final List<Enemigo> grupoEnemigo;
    int turno;
    Scanner sc;


    //CONSTRUCTOR
    public Combate(List<Personaje> grupo, List<Enemigo> grupoEnemigo) {
        //Inicializamos los datos de este combate
        this.grupo = grupo;
        this.grupoEnemigo = grupoEnemigo;
        sc = new Scanner(System.in);
        turno = 0;
    }


    //METODOS
    public void comenzarCombate(){
        do {
            mostrarEstadoGrupo();

            turno++;
            System.out.println("\nEl combate Continua");
            System.out.println("Turno: " + turno);

            //Iteramos sobre cada uno de los personajes para que actuen
            for (Personaje personaje : grupo){
                //EL PROBLEMA DE ESTE SISTEMA ES QUE SIGUE CORRIENDO AUNQUE HAYA ACABADO EL COMBATE;
                // DE MOMENTO NO DEBERÍA SER UN PROBLEMA

                //Comprobamos que el combate no haya acabado
                if (!comprobarDerrota() && !comprobarVictoria()){
                    boolean noHaActuado = false;
                    int accion = 0;
                    //Al empezar su turno, deja de defenderse, en caso de que se estuviera defendiendo.
                    personaje.setSe_defiende(false);
                    System.out.println("\n" + personaje.getNombre() + " actua!");

                    do {
                        System.out.println(personaje.menuCombate());
                        accion = sc.nextInt();
                        noHaActuado = realizarAccion(accion, personaje);
                    } while (!noHaActuado);
                }
            }

            //TURNO DE LOS ENEMIGOS
            grupoEnemigo.get(0).ataqueNormal(grupo);
            grupoEnemigo.get(1).ataqueNormal(grupo);
            grupoEnemigo.get(2).ataqueNormal(grupo);


        } while (!comprobarDerrota() && !comprobarVictoria());
        //Mensajes de Victoria/Derrota
        if (comprobarDerrota()){
            System.out.println("HAS PERDIDO");
        } else {
            System.out.println("HAS GANADO");
        }

        //Se acaba el combate y cerramos el Scanner
        sc.close();


        //HAY QUE CONTROLAR RECOMPENSAS O DERROTAS
        //NO ES PRIORIDAD AHORA MISMO
    }



    //----CONDICIONES Y COMPROBACIONES----//
    public boolean comprobarDerrota(){
        boolean derrota = false;
        int contadorMuertes = 0;

        //Contamos cuantos miembros del grupo han muerto
        for (Personaje personaje : grupo){
            if (personaje.isEsta_muerto()){
                contadorMuertes++;
            }
        }
        //Vemos si todos han muerto
        if (contadorMuertes == grupo.size()){
            derrota = true;
        }
        return derrota;
    }
    public boolean comprobarVictoria(){
        boolean victoria = false;
        int contadorMuertes = 0;

        //Contamos cuantos enemigos del grupo han muerto
        for (Enemigo enemigo : grupoEnemigo){
            if (enemigo.isEsta_muerto()){
                contadorMuertes++;
            }
        }
        //Vemos si todos han muerto
        if (contadorMuertes == grupoEnemigo.size()){
            victoria = true;
        }
        return victoria;
    }

    // ---------------------------------------------------- //
    //SWITCH CON EL QUE REALIZAREMOS LA ACCION SELECCIONADA
    public boolean realizarAccion(int accion, Personaje personaje){
        boolean haActuado = true;

        switch (accion){
            //ATACAR
            case 1:
                System.out.println("\n¿A quien quieres atacar?");
                //Seleccionamos al enemigo y atacamos
                personaje.ataqueNormal(grupoEnemigo.get(seleccionarEnemigo()));
                break;

            case 2:
                System.out.println("Se abre menu de Habilidades");
                break;

            case 3:
                personaje.setSe_defiende(true);
                System.out.println(personaje.getNombre() + " se defiende!");
                break;

            case 4:
                System.out.println("Se abre menu de Objetos");
                break;

            default:
                System.out.println("Opcion no valida, intentalo de nuevo");
                haActuado = false;
                break;
        }
        return haActuado;
    }



    //Método que nos permitirá seleccionar a un Enemigo
    public int seleccionarEnemigo(){
        int contador = 0;
        int eleccion = 0;
        //Hacemos una lista en las que guardaremos las elecciones válidas
        List<Integer> eleccionesValidas = new ArrayList<>();
        Enemigo enemigoSeleccionado = null;

        //Iteramos sobre el grupo enemigo para hacer un menu e imprimirlo
        for (Enemigo enemigo : grupoEnemigo){
            //Nos aseguramos de que solo salgan los que no están muertos
            if (!enemigo.isEsta_muerto()){
                System.out.println("(" + contador + ") " + enemigo.getNombre());
                eleccionesValidas.add(contador);
            }
            contador++;
        }

        //Nos aseguramos de que no se pueda salir hasta elegir un enemigo
        boolean enemigoNoElegido = true;
        do {
            eleccion = sc.nextInt();
            if (eleccionesValidas.contains(eleccion)){
                enemigoNoElegido = false;
            } else {
                System.out.println("Opcion no valida, intentalo de nuevo");
            }
        }while (enemigoNoElegido);

        return eleccion;
    }



    //MENUS Y DESCRIPCIONES
    //Metodo para mostrar el estado en mitad de combate
    public void mostrarEstadoGrupo(){
        for (Personaje personaje : grupo){
            System.out.print(personaje.getNombre() + "\t\t\t");
        }
        System.out.println();
        for (Personaje personaje : grupo){
            System.out.print("----------\t\t");
        }
        System.out.println();
        for (Personaje personaje : grupo){
            System.out.print(personaje.getPs() + " Ps\t\t\t");
        }
        System.out.println();
        for (Personaje personaje : grupo){
            System.out.print(personaje.getPm() + " Pm\t\t\t");
        }
        System.out.println();
    }
}