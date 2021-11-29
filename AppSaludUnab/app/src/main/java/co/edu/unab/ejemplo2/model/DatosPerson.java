package co.edu.unab.ejemplo2.model;

import java.io.Serializable;
import java.util.HashMap;

public class DatosPerson implements Serializable {
    String name;
    String lastName;
    String email;
    //int cantidadRegistros;


    public DatosPerson() {

    }

    public DatosPerson(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        //this.cantidadRegistros = cantidadRegistros;
        this.email = email;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /*

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

     */

    public HashMap<String, Object> getMapa() {
        HashMap<String, Object> mapaResultado = new HashMap<String, Object>();
        mapaResultado.put("nombre", getName());
        mapaResultado.put("apellido", getLastName());
        mapaResultado.put("correo", getEmail());
        //mapaResultado.put("cantidadRegistros", getCantidadRegistros());


        return mapaResultado;
    }
}

