package co.edu.unab.ejemplo2.model;

import java.io.Serializable;
import java.util.HashMap;

public class Informe implements Serializable {
    String email;
    String edad;
    String peso;
    String altura;
    Float imc;
    Float mBasal;
    int estadoSalud;
    String fecha;
    boolean genero;

    public Informe() {

    }

    public Informe(String email, String edad, String peso, String altura, float imc, float mBasal, int estadoSalud, String fecha, boolean genero) {
        this.email = email;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.imc = imc;
        this.mBasal = mBasal;
        this.estadoSalud = estadoSalud;
        this.fecha = fecha;
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public float getmBasal() {
        return mBasal;
    }

    public void setmBasal(Float mBasal) {
        this.mBasal = mBasal;
    }

    public int getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(int estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean getGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public HashMap<String, Object> getMapa() {
        HashMap<String, Object> mapaResultado = new HashMap<String, Object>();
        mapaResultado.put("email", getEmail());
        mapaResultado.put("edad", getEdad());
        mapaResultado.put("peso", getPeso());
        mapaResultado.put("altura", getAltura());
        mapaResultado.put("imc", getImc());
        mapaResultado.put("m_basal", getmBasal());
        mapaResultado.put("estadoSalud", getEstadoSalud());
        mapaResultado.put("fecha", getFecha());
        mapaResultado.put("genero", getGenero());

        return mapaResultado;
    }
}
