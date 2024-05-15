package Personajes;

public class Personaje implements Comparable<Personaje> {
    //ATRIBUTOS
    String nombre;

    protected int max_ps;
    protected int ps;
    protected int max_pm;
    protected int pm;
    protected int ataque;
    protected int magia;
    protected int armadura;
    protected int velocidad;

    protected boolean se_defiende = false;
    protected boolean esta_muerto = false;


    //CONSTRUCTOR
    public Personaje(String nombre, int max_ps, int max_pm, int ataque, int magia, int armadura, int velocidad) {
        //Cuando creamos al personaje sus atributos deberían estar al máximo
        this.nombre = nombre;
        this.max_ps = max_ps;
        this.ps = max_ps;
        this.max_pm = max_pm;
        this.pm = max_pm;
        this.ataque = ataque;
        this.magia = magia;
        this.armadura = armadura;
        this.velocidad = velocidad;
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
        } else this.ps = Math.min(ps, max_ps);
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
        this.pm = Math.max(pm, 0);
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
    public int getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }



    //MÉTODOS//
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


    @Override
    public int compareTo(Personaje o) {
        return  o.velocidad - this.velocidad;
    }
}