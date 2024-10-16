package Personajes.Enemigos;

import Personajes.Enemigo;

public class DuendiLemon extends Enemigo {
    //ATRIBUTOS

    //CONSTRUCTOR
    public DuendiLemon(String nombre, int max_ps, int max_pm, int ataque, int magia, int armadura, int velocidad, int xpDropped, int goldDropped) {
        super(nombre, max_ps, max_pm, ataque, magia, armadura, velocidad, xpDropped, goldDropped);
    }

    public DuendiLemon(String nombre){
        setNombre(nombre);
        setMax_ps(100);
        setPs(max_ps);
        setMax_pm(20);
        setPm(max_pm);
        setAtaque(50);
        setMagia(20);
        setArmadura(20);
        setVelocidad(30);
        setXpDropped(2);
        setGoldDropped(10);
        }


    //MÉTODOS
}