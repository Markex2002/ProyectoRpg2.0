package Personajes;

public abstract class Personaje {
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
    public Personaje(String nombre,int max_ps, int max_pm, int ataque, int magia, int armadura) {
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
            esta_muerto = true;
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

        String descripcion = "\n" + nombre;
        descripcion += "\n--------------------";

        descripcion += "\nPs: " + max_ps + " / " + ps;
        descripcion += "\nPm: " + max_pm + " / " + pm;
        descripcion += "\nAtaque: " + ataque;
        descripcion += "\nMagia: " + magia;
        descripcion += "\nArmadura: " + armadura;

        return descripcion;
    }

    public String menuCombate(){
        String menu = "";

        menu += "(1)Atacar\t(3)Defender";
        menu += "\n(2)Habilidades\t(4)Objetos";

        return menu;
    }



    //---       METODOS DE COMBATE      ---//
    //Método para calcular el daño inflingido
    public void ataqueNormal(Enemigo enemigo){
        //Calculamos el daño infligido
        int totalDamage = ataque - enemigo.armadura;
        //Nos aseguramos de que el daño no sea menor que 0, porque entonces el enemigo se cura xD
        if (totalDamage <= 0){
            totalDamage = 0;
        }

        //Atacamos al enemigo y comprobamos si muere
        enemigo.setPs(enemigo.getPs() - totalDamage);
        System.out.println("\n" + enemigo.nombre + " recibe " + totalDamage + " de daño");

        if (enemigo.getPs() <= 0){
            enemigo.esta_muerto = true;
            System.out.println(enemigo.nombre + " ha sido derrotado\n");
        }
    }
}