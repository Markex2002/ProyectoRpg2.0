import Inventario.Bolsa;
import Personajes.Enemigo;
import Personajes.Aliado;
import Personajes.Personaje;

import java.util.*;

public class Combate {
    //ATRIBUTOS
    private final List<Aliado> grupoAliado;
    private final List<Enemigo> grupoEnemigo;
    private final List<Personaje> ordenacionTurnos;
    //DARLE FINAL A BOLSA PODRÍA DAR PROBLEMAS, ESTAR ATENTO
    private final Bolsa bolsa;
    int turno;
    Scanner sc;


    //CONSTRUCTOR
    public Combate(List<Aliado> grupoAliado, List<Enemigo> grupoEnemigo, Bolsa bolsa) {
        sc = new Scanner(System.in);
        turno = 0;
        //Inicializamos los datos de este combate
        this.grupoAliado = grupoAliado;
        this.grupoEnemigo = grupoEnemigo;
        this.bolsa = bolsa;
        //Creamos una lista con los turnos que se usaran en combate

        //SI USAMOS UNA LISTA tIPO SET, EL ARRAY SE ORDENA SOLO, PERO
        //LOS PERSONAJES QUE TENGAN LA MISMA VELOCIDAD SE SOLAPAN.
        this.ordenacionTurnos = new ArrayList<>();
        ordenacionTurnos.addAll(grupoAliado);
        ordenacionTurnos.addAll(grupoEnemigo);

        //ORDENAMOS A LOS COMBATIENTES SEGUN SU VELOCIDAD MEDIANTE UN COMPARATOR
        //ESTO SE PUEDE OPTIMIZAR?
        //DEBERIA ELIMINAR LOS COMPARABLES DE LAS CLASES?
        ordenacionTurnos.sort((o1, o2) ->  o2.getVelocidad() - o1.getVelocidad());
    }


    //METODOS
    public void comenzarCombate(){
        do {
            mostrarEstadoGrupo();

            turno++;
            System.out.println("\nEl combate Continua");
            System.out.println("Turno: " + turno);


            //NUEVO METODO, ITERAMOS SOBRE TODOS PARA PODER USAR EL SISTEMA DE VELOCIDADES Y TURNOS
            for(Personaje personaje : ordenacionTurnos){
                //Comprobamos que el combate no haya acabado
                if (!comprobarDerrota() && !comprobarVictoria()) {
                    //Comprobamos también que el personaje no este Muerto
                    if (!personaje.isEsta_muerto()){
                        //CASO ALIADO
                        if (personaje instanceof Aliado aliado) {
                            boolean noHaActuado;
                            int accion;
                            //Al empezar su turno, deja de defenderse, en caso de que se estuviera defendiendo.
                            aliado.setSe_defiende(false);
                            System.out.println("\n" + aliado.getNombre() + " actua!");

                            //Hasta que no hayamos actuado no nos salimos del bucle
                            do {
                                System.out.println(aliado.menuCombate());
                                accion = sc.nextInt();
                                noHaActuado = realizarAccion(accion, aliado);
                            } while (!noHaActuado);
                        }
                        //CASO ENEMIGO
                        else if (personaje instanceof Enemigo enemigo) {
                            enemigo.setSe_defiende(false);
                            enemigo.ataqueNormal(grupoAliado);
                        }
                    }
                }

                //DE MOMENTO ESTO FALLA, CONTROLAR MUERTES MEDIANTE CONDICIONALES
                //Controlamos que, en el caso de que haya muerto alguien al
                //final del turno de este personaje, sacarle de la lista de Turnos mediante el uso de Lambdas o Streams
                //ordenacionTurnos.removeIf(Personaje::isEsta_muerto);
            }
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
        for (Aliado aliado : grupoAliado){
            if (aliado.isEsta_muerto()){
                contadorMuertes++;
            }
        }
        //Vemos si todos han muerto
        if (contadorMuertes == grupoAliado.size()){
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
    public boolean realizarAccion(int accion, Aliado aliado){
        boolean haActuado = true;

        switch (accion){
            //ATACAR
            case 1:
                System.out.println("\n¿A quien quieres atacar?");
                //Seleccionamos al enemigo y atacamos
                int eleccion = seleccionarEnemigo();
                if (eleccion < 0) {
                    haActuado = false;
                } else {
                    aliado.ataqueNormal(grupoEnemigo.get(eleccion));
                }
                break;

            case 2:
                System.out.println("Se abre menu de Habilidades");
                break;

            case 3:
                aliado.setSe_defiende(true);
                System.out.println(aliado.getNombre() + " se defiende!");
                break;

            case 4:
                //Usamos un objeto
                haActuado = aliado.usarObjeto(grupoAliado, bolsa, sc);
                //Comprobamos si hay que eliminar algún Objeto del inventario
                bolsa.comprobarCantidadObjetos();
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
        int eleccion;
        //Hacemos una lista en las que guardaremos las elecciones válidas
        List<Integer> eleccionesValidas = new ArrayList<>();
        eleccionesValidas.add(-1);

        //Iteramos sobre el grupo enemigo para hacer un menu e imprimirlo
        System.out.println("(-1) cancelar");
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
        } while (enemigoNoElegido);

        return eleccion;
    }



    //MENUS Y DESCRIPCIONES
    //Metodo para mostrar el estado en mitad de combate
    public void mostrarEstadoGrupo(){
        System.out.println();
        for (Aliado aliado : grupoAliado){
            System.out.print(aliado.getNombre() + "\t\t\t");
        }
        System.out.println();
        for (@SuppressWarnings("unused") Aliado aliado : grupoAliado){
            System.out.print("----------\t\t");
        }
        System.out.println();
        for (Aliado aliado : grupoAliado){
            System.out.print(aliado.getPs() + " Ps\t\t\t");
        }
        System.out.println();
        for (Aliado aliado : grupoAliado){
            System.out.print(aliado.getPm() + " Pm\t\t\t");
        }
        System.out.println();
    }
}