package co.edu.unab.ejemplo2.model;

import java.io.Serializable;
import java.util.HashMap;

public class Person implements Serializable {
    String name;
    String lastName;
    String id;
    String email;
    String password;

    public Person() {

    }

    public Person(String name, String lastName, String id, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
        this.password = password;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, Object> getMapa() {
        HashMap<String, Object> mapaResultado = new HashMap<String, Object>();
        mapaResultado.put("nombre", getName());
        mapaResultado.put("apellido", getLastName());
        mapaResultado.put("id", getId());
        mapaResultado.put("correo", getEmail());
        mapaResultado.put("clave", getPassword());

        return mapaResultado;
    }
}