package Inventario;

import java.util.ArrayList;
import java.util.List;

public class Bolsa {
    //Atributos
    private List<Objeto> inventario;
    private int gold;

    //Constructor
    public Bolsa() {
        this.inventario = new ArrayList<>();
        this.gold = 0;
    }


    
    //GETTERS AND SETTERS
    public List<Objeto> getInventario() {
        return inventario;
    }
    public void setInventario(List<Objeto> inventario) {
        this.inventario = inventario;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    


    //Métodos
    //Método para ver los objetos de nuestra Bolsa
    @Override
    public String toString() {
        String resultado = "";
        int contador = 0;
        //Miramos cuantos objetos tenemos en el inventario
        for (Objeto objeto : inventario) {
            resultado += "(" + contador + ")" + objeto.getNombre() + " x" + objeto.cantidad + "\n";
            contador++;
        }
        return resultado;
    }


    //Método para añadir objetos a nuestra bolsa de forma segura
    public void addObject(Objeto objetoNuevo) {
        boolean enBolsa = false;
        //Comprobamos si el objeto en cuestión esta ya o no en nuestra bolsa
        for (Objeto objeto : inventario) {
            if (objeto.getId() == objetoNuevo.getId()) {
                enBolsa = true;
                break;
            }
        }
        //Dependiendo de si está en bolsa o no,
        //metemos el Objeto nuevo, o añadimos cantidad al existente
        if (enBolsa){
            for (Objeto objeto : inventario) {
                if (objeto.getId() == objetoNuevo.getId()) {
                    objeto.cantidad += objetoNuevo.cantidad;
                }
            }
        } else {inventario.add(objetoNuevo);}
    }


    //Método para eliminar objetos del Inventario en caso de que se gasten
    public void comprobarCantidadObjetos(){
        inventario.removeIf(objeto -> objeto.cantidad <= 0);
    }


}