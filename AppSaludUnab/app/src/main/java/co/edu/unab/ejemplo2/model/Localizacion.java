package co.edu.unab.ejemplo2.model;

import java.io.Serializable;
import java.util.HashMap;

public class Localizacion implements Serializable {
    String email;
    double latitud;
    double longitud;

    public Localizacion() {

    }

    public Localizacion(String email, double latitud, double longitud) {
        this.email = email;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public HashMap<String, Object> getMapa() {
        HashMap<String, Object> mapaResultado = new HashMap<String, Object>();
        mapaResultado.put("correo", getEmail());
        mapaResultado.put("latitud", getLatitud());
        mapaResultado.put("longitud", getLongitud());

        return mapaResultado;
    }
}
