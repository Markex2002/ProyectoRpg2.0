package Inventario;

import java.util.ArrayList;
import java.util.List;

public class Bolsa {
    //Atributos
    private List<Objeto> inventario;

    //Constructor
    public Bolsa() {
        this.inventario = new ArrayList<>();
    }

    //GETTERS AND SETTERS
    public List<Objeto> getInventario() {
        return inventario;
    }
    public void setInventario(List<Objeto> inventario) {
        this.inventario = inventario;
    }


    //Métodos
    //Método para ver los objetos de nuestra Bolsa
    @Override
    public String toString() {
        String resultado = "";
        //Miramos cuantos objetos tenemos en el inventario
        for (Objeto objeto : inventario) {
            System.out.println(objeto.getNombre() + " x" + objeto.cantidad);
        }
        return resultado;
    }


    //Método para añadir objetos a nuestra bolsa de forma segura
    public void addObject(Objeto objetoNuevo) {
        boolean enBolsa = false;

        //Comprobamos si el objeto en cuesta ya esta o no en nuestra bolsa
        for (Objeto objeto : inventario) {
            if (objeto.getId() == objetoNuevo.getId()) {
                enBolsa = true;
                break;
            }
        }

        //Dependiendo de si esta en bolsa o no,
        // metemos el Objeto nuevo, o añadimos cantidad al existente
        if (enBolsa){
            for (Objeto objeto : inventario) {
                if (objeto.getId() == objetoNuevo.getId()) {
                    objeto.cantidad += objetoNuevo.cantidad;
                }
            }
        } else {
            inventario.add(objetoNuevo);
        }
    }
}