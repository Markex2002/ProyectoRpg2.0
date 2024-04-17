package Personajes;

import java.util.List;

public abstract class Enemigo {
    //ATRIBUTOS
    String nombre;

    int max_ps;
    int ps;
    int max_pm;
    int pm;
    int ataque;
    int magia;
    int armadura;

    boolean se_defiende = false;
    boolean esta_muerto = false;


    //CONSTRUCTOR
    public Enemigo(String nombre, int max_ps, int max_pm, int ataque, int magia, int armadura) {
        //Cuando creamos al personaje sus atributos deberian estar al maximo
        this.nombre = nombre;
        this.max_ps = max_ps;
        this.ps = max_ps;
        this.max_pm = max_pm;
        this.pm = max_pm;
        this.ataque = ataque;
        this.magia = magia;
        this.armadura = armadura;
    }



    //GETTERS AND SETTERS
    public int getMax_ps() {
        return max_ps;
    }
    public void setMax_ps(int max_ps) {
        this.max_ps = max_ps;
    }
    public int getPs() {
        return ps;
    }
    public void setPs(int ps) {
        if (ps < 0){
            this.ps = 0;
        } else {
            this.ps = ps;
        }
    }
    public int getMax_pm() {
        return max_pm;
    }
    public void setMax_pm(int max_pm) {
        this.max_pm = max_pm;
    }
    public int getPm() {
        return pm;
    }
    public void setPm(int pm) {
        if (pm < 0){
            this.pm = 0;
        } else {
            this.pm = pm;
        }
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getMagia() {
        return magia;
    }
    public void setMagia(int magia) {
        this.magia = magia;
    }
    public int getArmadura() {
        return armadura;
    }
    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }
    public boolean isSe_defiende() {
        return se_defiende;
    }
    public void setSe_defiende(boolean se_defiende) {
        this.se_defiende = se_defiende;
    }
    public boolean isEsta_muerto() {
        return esta_muerto;
    }
    public void setEsta_muerto(boolean esta_muerto) {
        this.esta_muerto = esta_muerto;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //METODOS//
    //---       MENUS       ---//
    @Override
    public String toString() {
        String descripcion = "";

        descripcion += nombre;
        descripcion += "\n--------------------";

        descripcion += "\n\nPs: " + max_ps + " / " + ps;
        descripcion += "\nPm: " + max_pm + " / " + pm;
        descripcion += "\nAtaque: " + ataque;
        descripcion += "\nMagia: " + magia;
        descripcion += "\nArmadura: " + armadura;

        return descripcion;
    }



    //---       METODOS DE COMBATE      ---//
    public void ataqueNormal(List<Personaje> grupoPersonajes){
        //Empezamos eligiendo a que personaje vamos a atacar
        Personaje personaje = elegirAtaqueAlAzar(grupoPersonajes);

        //Infligimos daño al personaje /////EN UN FUTURO CREAR UNA PROBABILIDAD DE ESQUIVE, FIJA O DINÁMICA
        int damageBase = ataque - personaje.armadura;
        int damageReceived = 0;
        //Que no sea menor que 0
        if (damageBase <= 0){
            damageBase = 0;
        }

        //Nos fijamos en si el personaje se está defendiendo
        if (personaje.isSe_defiende()){
            personaje.setPs(personaje.getPs() - damageBase/2);
            damageReceived = damageBase/2;
        } else {
            personaje.setPs(personaje.getPs() - damageBase);
            damageReceived = damageBase;
        }

        System.out.println("\n" + nombre + " ataca a " + personaje.getNombre() + "!");
        System.out.println(personaje.getNombre() + " recibe " + damageReceived + " de daño!");
    }

    //Metodo para atacar a un miembro aleatorio del grupo Aliado
    public Personaje elegirAtaqueAlAzar(List<Personaje> grupoPersonajes){
        //Hay que asegurarse de que no ataquen a un muerto
        boolean haElegido = false;
        Personaje personaje = null;
        int eleccion = (int)(Math.random() * (grupoPersonajes.size()) + 0);

        //Bucle hasta que se eliga al personaje
        do {
            //Miramos si esta muerto, si no, elegimos uno nuevo
            if (!grupoPersonajes.get(eleccion).isEsta_muerto()){
                haElegido = true;
            } else {
                eleccion = (int)(Math.random() * (grupoPersonajes.size()) + 0);
            }
        }while (!haElegido);

        return grupoPersonajes.get(eleccion);
    }
}