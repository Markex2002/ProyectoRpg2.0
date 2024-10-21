package Mazmorra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import Inventario.Bolsa;
import Inventario.Objeto;
import Inventario.ObjetosCombate.PlumaFenix;
import Inventario.ObjetosCombate.Pocion;
import Personajes.Aliado;

public class MazmorraFinal {
    //ATRIBUTOS
    String[][] dungeonMap;
    List<Objeto> treasureItemList;

    int treasureChests;
    int trapRooms;
    int enemyRooms;

    int xHeroes;
    int yHeroes;

    List<Aliado> grupoAliado;
    Bolsa bolsa;

    //El escaner de Mazmorra podria dar conflicto con el Scanner de Combate, estar atento....
    Scanner sc;





    //COSNTRUCTOR
    public MazmorraFinal(List<Aliado> grupoAliado, Bolsa bolsa) {
        //Inicializamos los atributos de esta Mazmorra
        sc = new Scanner(System.in);
        this.grupoAliado = grupoAliado;
        this.bolsa = bolsa;

        //Decidimos como de grande sera nuestra mazmorra
        this.dungeonMap = new String[5][5];

        //Randomizamos las salas de nuestra mazmorra
        this.treasureChests = numRandomizer(3, 1);
        this.trapRooms= numRandomizer(1, 0);
        this.enemyRooms = numRandomizer(3, 1);

        //Creamos los posibles tesoros que pueden salir de un cofre
        treasureItemList = new ArrayList<>();
        treasureItemList.add(new Pocion(2));
        treasureItemList.add(new Pocion(1));
        treasureItemList.add(new PlumaFenix(1));


        //Creamos nuestra Mazmorra
        dungeonMapCreator();
    }



    //GETTERS AND SETTERS
    public String[][] getDungeonMap() {
        return dungeonMap;
    }
    public void setDungeonMap(String[][] dungeonMap) {
        this.dungeonMap = dungeonMap;
    }
    public int getTreasureChests() {
        return treasureChests;
    }
    public void setTreasureChests(int treasureChests) {
        this.treasureChests = treasureChests;
    }
    public int getTrapRooms() {
        return trapRooms;
    }
    public void setTrapRooms(int trapRooms) {
        this.trapRooms = trapRooms;
    }
    public int getEnemyRooms() {
        return enemyRooms;
    }
    public void setEnemyRooms(int enemyRooms) {
        this.enemyRooms = enemyRooms;
    }
    public List<Aliado> getGrupoAliado() {
        return grupoAliado;
    }
    public void setGrupoAliado(List<Aliado> grupoAliado) {
        this.grupoAliado = grupoAliado;
    }
    



    //METODOS
    /////METODOS PARA LA CREACION DE LA MAZMORRA/////
    public void dungeonMapCreator(){
        //Empezaremos colocando la casilla en la que empezaran los heroes
        dungeonMap[0][2] = "H";
        dungeonMap[4][2] = "B";

        //Colocamos las salas del tesoro
        for (int i = 0; i < treasureChests; i++) {
            int x = numRandomizer(4, 0);
            int y = numRandomizer(4, 0);

            //Comprobamos si la habitacion generada esta vacia
            if (dungeonMap[x][y] == null) {
                dungeonMap[x][y] = "T";
            } else {
                i--;
            }
        }


        //Colocamos las salas de Enemigos
        for (int i = 0; i < enemyRooms; i++) {
            int x = numRandomizer(4, 0);
            int y = numRandomizer(4, 0);

            //Comprobamos si la habitacion generada esta vacia
            if (dungeonMap[x][y] == null) {
                dungeonMap[x][y] = "E";
            } else {
                i--;
            }
        }

        //Rellenamos los espacios vacios con Habitaciones normales
        for (int i = 0; i < dungeonMap.length; i++) {
            for (int j = 0; j < dungeonMap[i].length; j++) {
                if (dungeonMap[i][j] == null) {
                    dungeonMap[i][j] = "r";
                }
            }
        }
    }


    //Metodo que usaremos para mirar nuestro mapa
    public void watchMap(){
        for (int i = dungeonMap.length - 1; i >= 0; i--) {
            System.out.println();
            for (int j = 0; j < dungeonMap[i].length; j++) {
                System.out.print(dungeonMap[i][j] + "  ");
            }
        }
        System.out.println();
    }


    //Metodo que usaremos para rellenar nuestras casillas
    public void rellenarCasilla(int x, int y, String roomType){
        if (dungeonMap[x][y] == null){
            dungeonMap[x][y] = roomType;
        } 
    }







    /////METODOS PARA PODER JUGAR LA MAZMORRA/////
    public void exploreDungeon(){
        //Buscamos y actualizamos la posicion de los Heroes
        posicionHeroes();
        //Una vez encontrada la posicion de nuestros Heroes
        //Mostramos las opciones que tiene en su disposicion.
        List<Integer> posiblesMovimientos;
        boolean finMazmorra = false;


        do {
            posiblesMovimientos = menuMovimiento(xHeroes, yHeroes);
            realizarMovimientos(posiblesMovimientos);
        } while (!finMazmorra);
    }



    public void realizarMovimientos(List<Integer> posiblesMovimientos){
        int accion = 0;
        do {
            //Elegimos a donde nos queremos mover
            accion = sc.nextInt();
        } while (!elegirMovimiento(accion, posiblesMovimientos, xHeroes, yHeroes));
        posicionHeroes();
        watchMap();
    }



    //Metodo para buscar y actualizar la posicion de nuestro Grupo
    public void posicionHeroes(){
        for (int i = 0; i < dungeonMap.length; i++) {
            for (int j = 0; j < dungeonMap[i].length; j++) {
                if(dungeonMap[i][j] == "H") {
                    xHeroes = i;
                    yHeroes = j;
                }
            }
        }
    }



    //Metodo para imprimir el movimiento de los heroes y ver sus movimientos disponibles
    public List<Integer> menuMovimiento(int xHeroes, int yHeroes){
        List<Integer> movimientosPosibles = new ArrayList<>();
        String arriba = "(0) moverve arriba";
        String abajo = "(1) moverve abajo";
        String derecha = "(2) moverve a la derecha";
        String izquierda = "(3) moverve a la Izquierda";

        //Hacemos comprobaciones para ver en que 
        //direcciones nos podemos mover
        //Arriba Abajo
        if (xHeroes == dungeonMap.length - 1) {
            System.out.println(abajo);
            movimientosPosibles.add(1);
        } else if (xHeroes == 0) {
            System.out.println(arriba);
            movimientosPosibles.add(0);
        } else {
            System.out.println(arriba);
            System.out.println(abajo);
            movimientosPosibles.add(0);
            movimientosPosibles.add(1);
        }
        //Derecha Izquierda
        if (yHeroes == dungeonMap[xHeroes].length - 1) {
            System.out.println(izquierda);
            movimientosPosibles.add(3);
        } else if (yHeroes == 0) {
            System.out.println(derecha);
            movimientosPosibles.add(2);
        } else {
            System.out.println(derecha);
            System.out.println(izquierda);
            movimientosPosibles.add(2);
            movimientosPosibles.add(3);
        }

        return movimientosPosibles;
    }



    //Metodo que usaremos para movernos por el mapa
    public boolean elegirMovimiento(int movimiento, List<Integer> movimientosDisponibles, int xHeroes, int yHeroes){
        boolean movimientoRealizado = false;
                //Realizamos la accion de movernos
                switch (movimiento) {
                    case 0:
                    if (movimientosDisponibles.contains(movimiento)) {
                        System.out.println("Te mueves arriba");
                        dungeonMap[xHeroes][yHeroes] = "r";
                        eventoSala(xHeroes + 1, yHeroes);
                        dungeonMap[xHeroes + 1][yHeroes] = "H";
                        movimientoRealizado = true;
                    } else {
                        System.out.println("Movimiento no valido");
                    } 
                        break;
                    case 1:
                    if (movimientosDisponibles.contains(movimiento)) {
                        System.out.println("Te mueves abajo");
                        dungeonMap[xHeroes][yHeroes] = "r";
                        eventoSala(xHeroes - 1, yHeroes);
                        dungeonMap[xHeroes - 1][yHeroes] = "H";
                        movimientoRealizado = true;
                    } else {
                        System.out.println("Movimiento no valido");
                    }
                        
                        break;
                    case 2:
                    if (movimientosDisponibles.contains(movimiento)) {
                        System.out.println("Te mueves a la derecha");
                        dungeonMap[xHeroes][yHeroes] = "r";
                        eventoSala(xHeroes, yHeroes + 1);
                        dungeonMap[xHeroes][yHeroes + 1] = "H";
                        movimientoRealizado = true;
                    } else {
                        System.out.println("Movimiento no valido");
                    }
                        
                        break;
                    case 3:
                    if (movimientosDisponibles.contains(movimiento)) {
                        System.out.println("Te mueves a la izquierda");
                        dungeonMap[xHeroes][yHeroes] = "r";
                        eventoSala(xHeroes, yHeroes - 1);
                        dungeonMap[xHeroes][yHeroes - 1] = "H";
                        movimientoRealizado = true;
                    } else {
                        System.out.println("Movimiento no valido");
                    }
                        break;
                
                    default:
                    System.out.println("Acción no válida");
                        break;
                }

        return movimientoRealizado; 
    }




    //EVENTOS DE SALAS
    public void eventoSala(int x, int y){
        String tipoSala = dungeonMap[x][y];
        switch (tipoSala) {
            case "E":
            enemyRoomEvent();
                break;

            case "T":
            treasureRoomEvent();
                break;

            case "r":
            System.out.println("Encontraste una habitacion normal");
                break;
        
            default:
                break;
        }
    }


    public void treasureRoomEvent(){
        Collections.shuffle(treasureItemList);
        System.out.println("¡Un cofre!");
        System.out.println("Obtuviste: " + treasureItemList.get(0).getNombre() + " x" + treasureItemList.get(0).getCantidad());
        bolsa.addObject(treasureItemList.get(0));
    }

    public void enemyRoomEvent(){
        System.out.println("Aparece un Enemigo");
    }



    

    //Metodo para randomizar las numeros en un rango
    public Integer numRandomizer(int max, int min){
        int num;
        num = (int)(Math.random() * max) + min;     

        return num;
    }
}