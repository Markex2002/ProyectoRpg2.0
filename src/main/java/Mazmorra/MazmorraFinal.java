package Mazmorra;

import java.util.List;

import Personajes.Aliado;

public class MazmorraFinal {
    //ATRIBUTOS
    String[][] dungeonMap;

    int treasureChests;
    int trapRooms;
    int enemyRooms;

    List<Aliado> grupoAliado;





    //COSNTRUCTOR
    public MazmorraFinal(List<Aliado> grupoAliado) {
        this.grupoAliado = grupoAliado;

        //Decidimos como de grande sera nuestra mazmorra
        this.dungeonMap = new String[5][5];

        //Randomizamos las salas de nuestra mazmorra
        this.treasureChests = numRandomizer(3, 1);
        this.trapRooms= numRandomizer(1, 0);
        this.enemyRooms = numRandomizer(3, 1);

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
    public void dungeonMapCreator(){
        //Empezaremos colocando la casilla en la que empezaran los heroes
        dungeonMap[0][2] = "h";


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
                System.out.print(dungeonMap[i][j] + " ");
            }
        }
    }



    //Metodo que usaremos para rellenar nuestras casillas
    public void rellenarCasilla(int x, int y, String roomType){
        if (dungeonMap[x][y] == null){
            dungeonMap[x][y] = roomType;
        } 
    }



    //Metodo para comenzar a jugar la mazmorra
    public void exploreDungeon(){

    }
    

    //Metodo para randomizar las salas de la mazmorra
    public Integer numRandomizer(int max, int min){
        int num;
        num = (int)(Math.random() * max) + min;     

        return num;
    }
}