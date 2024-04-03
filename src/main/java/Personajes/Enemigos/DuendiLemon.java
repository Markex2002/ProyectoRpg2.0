package Personajes.Enemigos;

import Personajes.Enemigo;

public class DuendiLemon extends Enemigo {
    public DuendiLemon(String nombre, int max_ps, int max_pm, int ataque, int magia, int armadura) {
        super(nombre, max_ps, max_pm, ataque, magia, armadura);
    }

    @Override
    public int ataqueNormal() {
        return 0;
    }

    @Override
    public int recibirAtaque(int damageReceived) {
        return 0;
    }
}
